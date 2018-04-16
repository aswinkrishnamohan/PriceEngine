package com.priceengine.helpers;

import java.util.ArrayList;

import com.priceengine.entity.Products;
import com.priceengine.entity.SurveyData;

public interface PriceCalculator {
	public double calcFrequentPrice(Products product, ArrayList<SurveyData> surveryData);
}
