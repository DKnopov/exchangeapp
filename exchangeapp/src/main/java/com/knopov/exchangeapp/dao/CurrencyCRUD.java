package com.knopov.exchangeapp.dao;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;
import com.knopov.exchangeapp.entity.Currency;

public interface CurrencyCRUD {

	// HELPER METHOD
	public Currency addCurrencyManually(Currency cur);

	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order);

}
