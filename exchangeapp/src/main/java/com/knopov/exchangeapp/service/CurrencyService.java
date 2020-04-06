package com.knopov.exchangeapp.service;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;

public interface CurrencyService {

	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order);

}
