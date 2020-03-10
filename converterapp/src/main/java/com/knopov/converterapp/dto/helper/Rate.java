package com.knopov.converterapp.dto.helper;

import java.util.Map;

public class Rate {
	
	private Map<String, CustomCurrencies> datesAndCurrencies;

	public Rate() {
	}

	public Rate(Map<String, CustomCurrencies> datesAndCurrencies) {
		this.datesAndCurrencies = datesAndCurrencies;
	}

	public Map<String, CustomCurrencies> getDatesAndCurrencies() {
		return datesAndCurrencies;
	}

	public void setDatesAndCurrencies(Map<String, CustomCurrencies> datesAndCurrencies) {
		this.datesAndCurrencies = datesAndCurrencies;
	}
	

}
