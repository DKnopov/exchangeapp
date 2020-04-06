package com.knopov.exchangeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knopov.exchangeapp.dao.CurrencyCRUD;
import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyCRUD currencyCRUD;

	@Autowired
	public CurrencyServiceImpl(CurrencyCRUD currencyCRUD) {
		this.currencyCRUD = currencyCRUD;
	}


	@Override
	@Transactional
	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order) {
	
		return currencyCRUD.getCurrencies(order);
	}
	
	

	/*
	 * @Override
	 * 
	 * @Transactional public CurrencyResponseDTO getCurrencies(FoundAndNotFound
	 * foundAndNotFound) { return currencyCRUD.getCurrencies(foundAndNotFound); }
	 */

}
