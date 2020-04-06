package com.knopov.exchangeapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;
import com.knopov.exchangeapp.entity.Currency;
import com.knopov.exchangeapp.service.CurrencyService;

@RestController
public class CurrencyRestController {

	private CurrencyService currencyService;

	@Autowired
	public CurrencyRestController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	
	@PostMapping("/currencies")
	public CurrencyResponseDTO getCurrencies(@RequestBody CurrencyQueryDTO query) {
		
		return currencyService.getCurrencies(query);
	}

}
