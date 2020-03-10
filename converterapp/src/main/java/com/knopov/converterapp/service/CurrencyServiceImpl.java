package com.knopov.converterapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knopov.converterapp.dao.CurrencyCRUD;
import com.knopov.converterapp.dto.CurrencyQueryDTO;
import com.knopov.converterapp.dto.helper.FoundAndNotFound;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyCRUD currencyCRUD;

	@Autowired
	public CurrencyServiceImpl(CurrencyCRUD currencyCRUD) {
		this.currencyCRUD = currencyCRUD;
	}

	@Override
	@Transactional
	public FoundAndNotFound transitionalResult(CurrencyQueryDTO order) {
		return currencyCRUD.transitionalResult(order);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public CurrencyResponseDTO getCurrencies(FoundAndNotFound
	 * foundAndNotFound) { return currencyCRUD.getCurrencies(foundAndNotFound); }
	 */

}
