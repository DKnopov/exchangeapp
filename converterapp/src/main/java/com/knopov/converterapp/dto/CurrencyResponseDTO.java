package com.knopov.converterapp.dto;

import java.util.List;

import com.knopov.converterapp.dto.helper.CustomError;
import com.knopov.converterapp.dto.helper.Rate;

public class CurrencyResponseDTO {
	private List<Rate> rates;
	private List<CustomError> errors = null;

	public CurrencyResponseDTO() {
	}

	public CurrencyResponseDTO(List<Rate> rates, List<CustomError> errors) {
		this.rates = rates;
		this.errors = errors;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public List<CustomError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomError> errors) {
		this.errors = errors;
	}

}
