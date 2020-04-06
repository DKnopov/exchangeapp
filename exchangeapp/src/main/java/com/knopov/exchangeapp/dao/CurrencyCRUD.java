package com.knopov.exchangeapp.dao;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;

public interface CurrencyCRUD {


	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order);

}
