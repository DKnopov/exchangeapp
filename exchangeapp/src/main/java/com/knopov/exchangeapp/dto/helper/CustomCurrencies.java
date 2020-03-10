package com.knopov.exchangeapp.dto.helper;

import java.util.Map;

public class CustomCurrencies {

	private Map<String, Double> customCurrencies;

	public CustomCurrencies() {
	}

	public CustomCurrencies(Map<String, Double> customCurrencies) {
		this.customCurrencies = customCurrencies;
	}

	public Map<String, Double> getCustomCurrencies() {
		return customCurrencies;
	}

	public void setCustomCurrencies(Map<String, Double> customCurrencies) {
		this.customCurrencies = customCurrencies;
	}

}
