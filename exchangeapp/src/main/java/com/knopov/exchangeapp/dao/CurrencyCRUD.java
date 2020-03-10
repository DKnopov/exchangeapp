package com.knopov.exchangeapp.dao;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.helper.FoundAndNotFound;

public interface CurrencyCRUD {
	
	public FoundAndNotFound transitionalResult(CurrencyQueryDTO order);
	
	/*
	 * public CurrencyResponseDTO getCurrencies(FoundAndNotFound foundAndNotFound);
	 */

}
