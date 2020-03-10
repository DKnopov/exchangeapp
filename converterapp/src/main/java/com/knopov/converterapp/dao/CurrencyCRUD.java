package com.knopov.converterapp.dao;

import com.knopov.converterapp.dto.CurrencyQueryDTO;
import com.knopov.converterapp.dto.helper.FoundAndNotFound;

public interface CurrencyCRUD {
	
	public FoundAndNotFound transitionalResult(CurrencyQueryDTO order);
	
	/*
	 * public CurrencyResponseDTO getCurrencies(FoundAndNotFound foundAndNotFound);
	 */

}
