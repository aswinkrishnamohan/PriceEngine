package com.priceengine.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

import com.priceengine.entity.Products;
import com.priceengine.entity.SurveyData;
import com.priceengine.helpers.PriceCalculatorImpl;

public class PriceEngine {

	BufferedReader bReader;
	
	public PriceEngine(BufferedReader bReader) {
		super();
		this.bReader = bReader;
	}

	public static void main(String[] args) {
		try {
			HashSet<Products> products;
			ArrayList<SurveyData> surveyData;
			PriceEngine priceEngine = new PriceEngine(new BufferedReader(new InputStreamReader(System.in)));
			products = priceEngine.getProducts();
			surveyData = priceEngine.getProductSurveyData();
			PriceCalculatorImpl pc = new PriceCalculatorImpl();
			for(Products pr : products) {
				System.out.println(pr.getProductCode() + " " +pc.calcFrequentPrice(pr, surveyData));
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashSet<Products> getProducts() throws NumberFormatException, IOException {
		HashSet<Products> productList = new HashSet<>();
		System.out.println("Please enter the number of products followed by product details: ");
		int numberOfProducts = Integer.parseInt(bReader.readLine());

		try {
			if (numberOfProducts > 0) {
				for (int i = 0; i < numberOfProducts; i++) {
					String product = bReader.readLine();
					String[] productDetail = product.split(" ");
					if(productDetail.length == 3) {
						Products pr = new Products(productDetail[0], productDetail[1], productDetail[2]);
						productList.add(pr);
					} else {
						throw new Exception("Format of Product details entered is incorrect.");
					}
				}
			} else {
				throw new Exception("Number of products cannot be zero.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	public ArrayList<SurveyData> getProductSurveyData() throws NumberFormatException, IOException {
		ArrayList<SurveyData> surveyList = new ArrayList<>();
		System.out.println("Please enter the number of products followed by product details: ");
		int numberOfSurvey = Integer.parseInt(bReader.readLine());

		try {
			if (numberOfSurvey > 0) {
				for (int i = 0; i < numberOfSurvey; i++) {
					String product = bReader.readLine();
					String[] productDetail = product.split(" ");
					if(productDetail.length == 3) {
						SurveyData pr = new SurveyData(productDetail[0], productDetail[1], Double.parseDouble(productDetail[2]));
						surveyList.add(pr);
					} else {
						throw new Exception("Format of Survey details entered is incorrect.");
					}
				}
			} else {
				throw new Exception("Number of surveys cannot be zero.");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return surveyList;
	}

}
