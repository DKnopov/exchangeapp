package com.knopov.exchangeapp.service;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;
import com.knopov.exchangeapp.entity.Currency;

public interface CurrencyService {

	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order);

	public Currency addFewCurrenciesManually(Currency cur);
	/*
	 * public CurrencyResponseDTO getCurrencies(FoundAndNotFound foundAndNotFound);
	 */
}
