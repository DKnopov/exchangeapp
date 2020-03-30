package com.knopov.exchangeapp.dao;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;
import com.knopov.exchangeapp.entity.Currency;

public interface CurrencyCRUD {

	//public FoundAndNotFound transitionalResult(CurrencyQueryDTO order);

	public Currency addFewCurrenciesManually(Currency cur);

	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order);

}
