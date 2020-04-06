package com.knopov.exchangeapp.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	protected Map<LocalDate, Map<String, Double>> rates;
	@JsonIgnore
	private String error = "No exchange rate data is available for the selected currency";

	public void addMoreCurrencies(List<Currency> currencies) {
		Map<String, Double> operMap;
		for (Currency currency : currencies) {
			operMap = new HashMap<String, Double>();
			if (rates.containsKey(currency.getDate())) {
				operMap = rates.get(currency.getDate());
			}
			operMap.put(currency.getCurrencyName(), currency.getValue());
			rates.put(currency.getDate(), operMap);
		}
	}

	public CurrencyResponseDTO createWithErrorDTO(List<Currency> needToFind) {
		Map<String, String> errors = new HashMap<String, String>();
		CurrencyResponseWithErrorsDTO withError = new CurrencyResponseWithErrorsDTO();
		withError.setRates(rates);
		for (Currency currency : needToFind) {
			if (!rates.containsKey(currency.getDate())) {
				errors.put(currency.getCurrencyName(), error);
			}
		}
		withError.setErrors(errors);
		if (errors.equals(null) || errors.isEmpty()) {
			return this;
		} else {
			return withError;
		}
	}

}
