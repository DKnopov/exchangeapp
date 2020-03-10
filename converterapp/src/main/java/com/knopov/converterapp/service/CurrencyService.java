package com.knopov.converterapp.service;

import com.knopov.converterapp.dto.CurrencyQueryDTO;
import com.knopov.converterapp.dto.helper.FoundAndNotFound;

public interface CurrencyService {
	public FoundAndNotFound transitionalResult(CurrencyQueryDTO order);
	
	/*
	 * public CurrencyResponseDTO getCurrencies(FoundAndNotFound foundAndNotFound);
	 */
}
