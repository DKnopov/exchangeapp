package com.knopov.exchangeapp.service;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.helper.FoundAndNotFound;

public interface CurrencyService {
	public FoundAndNotFound transitionalResult(CurrencyQueryDTO order);
	
	/*
	 * public CurrencyResponseDTO getCurrencies(FoundAndNotFound foundAndNotFound);
	 */
}
