package com.priceengine.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.priceengine.entity.Products;
import com.priceengine.entity.SurveyData;

public class PriceCalculatorImpl implements PriceCalculator {

	@Override
	public double calcFrequentPrice(Products product, ArrayList<SurveyData> surveyData) {
		double frequentPrice = 0;
		Supplier<Stream<SurveyData>> surveyDataStream = () -> surveyData.stream()
				.filter(e -> e.getProductCode().equalsIgnoreCase(product.getProductCode()));

		double averagePrice = surveyDataStream.get().mapToDouble(e -> e.getPrice()).average().getAsDouble();
		//assumed only the promotion price condition as the other all other prices are considered above 50% of the average price.
		Stream<SurveyData> withoutPromotionEligiblePrice = surveyDataStream.get()
				.filter(e -> e.getPrice() >= averagePrice * .5);
		
		double minEligiblePrice = withoutPromotionEligiblePrice.collect(Collectors.groupingBy(e->e.getPrice(), Collectors.counting())).entrySet().parallelStream().
				max((e1,e2)->{if(e1.getValue() > e2.getValue()) {
					return 1;
				} else if(e1.getValue() < e2.getValue()) {
					return -1;
				} else {
					if(e1.getKey() < e2.getKey())
						return 1;
					else return -1;
				}
				}).get().getKey();
				

		if (product.getDemandStatus().equalsIgnoreCase("H") && product.getSupplyStatus().equalsIgnoreCase("H")) {
			frequentPrice = minEligiblePrice;
		} else if (product.getDemandStatus().equalsIgnoreCase("L") && product.getSupplyStatus().equalsIgnoreCase("L")) {
			frequentPrice = minEligiblePrice * 1.1;
		} else if (product.getDemandStatus().equalsIgnoreCase("H") && product.getSupplyStatus().equalsIgnoreCase("L")) {
			frequentPrice = minEligiblePrice * 1.05;
		} else if (product.getDemandStatus().equalsIgnoreCase("L") && product.getSupplyStatus().equalsIgnoreCase("H")) {
			frequentPrice = minEligiblePrice * .95;
		}

		return frequentPrice;
	}

}
