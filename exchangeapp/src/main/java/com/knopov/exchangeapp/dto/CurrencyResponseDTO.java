package com.knopov.exchangeapp.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.knopov.exchangeapp.entity.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponseDTO {

	/*
	 * private Rate rates; private CustomError errors = null;
	 */
	private Map<LocalDate, Map<String, Double>> rates;

	public void addMoreCurrencies(List<Currency> currencies) {
		Map<String, Double> operMap = new HashMap<String, Double>();
		for (Currency currency : currencies) {
			if (rates.containsKey(currency.getDate())) {
				operMap = rates.get(currency.getDate());
				operMap.put(currency.getCurrencyName(), currency.getValue());
			} else {
				operMap.put(currency.getCurrencyName(), currency.getValue());
			}
			rates.put(currency.getDate(), operMap);
		}
	}
}
