package com.knopov.exchangeapp.dto.helper;

import java.time.LocalDate;
import java.util.Map;

public class Rate {
	
	private Map<LocalDate, CustomCurrencies> datesAndCurrencies;

	public Rate() {
	}

	public Rate(Map<LocalDate, CustomCurrencies> datesAndCurrencies) {
		this.datesAndCurrencies = datesAndCurrencies;
	}

	public Map<LocalDate, CustomCurrencies> getDatesAndCurrencies() {
		return datesAndCurrencies;
	}

	public void setDatesAndCurrencies(Map<LocalDate, CustomCurrencies> datesAndCurrencies) {
		this.datesAndCurrencies = datesAndCurrencies;
	}

	@Override
	public String toString() {
		return "Rate [datesAndCurrencies=" + datesAndCurrencies + "]";
	}
	
	
	


	

}
