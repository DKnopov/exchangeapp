package com.knopov.converterapp.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knopov.converterapp.dto.CurrencyQueryDTO;
import com.knopov.converterapp.dto.CurrencyResponseDTO;
import com.knopov.converterapp.dto.helper.CustomCurrencies;
import com.knopov.converterapp.dto.helper.FoundAndNotFound;
import com.knopov.converterapp.dto.helper.Rate;
import com.knopov.converterapp.entity.Currency;
import com.knopov.converterapp.service.CurrencyService;

@RestController
public class CurrencyRestController {

	private CurrencyService currencyService;

	@Autowired
	public CurrencyRestController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	
	//TODO to transfer all logic from handler
	
	@PostMapping("/currencies")
	public CurrencyResponseDTO getCurrencies(@RequestBody CurrencyQueryDTO query) {
		FoundAndNotFound foundAndNotFound = currencyService.transitionalResult(query);
		List<Currency> found = foundAndNotFound.getFound();
		List<Currency> needToFind = foundAndNotFound.getNeedToFind();
		CurrencyResponseDTO res = new CurrencyResponseDTO();
		Rate rate = new Rate();
		List<Rate> rates = new ArrayList<>();
		CustomCurrencies custCur = new CustomCurrencies();
		Map<String, Double> curMap = new HashMap<>();
		Map<String, CustomCurrencies> datesAndCurrencies = new HashMap<>();
		if (needToFind.isEmpty()) {
			for (Currency currency : found) {
				curMap.put(currency.getCurrencyName(), currency.getValue());
				custCur.setCustomCurrencies(curMap);
				datesAndCurrencies.put(currency.getDate(), custCur);
				rate.setDatesAndCurrencies(datesAndCurrencies);
				rates.add(rate);
			}
			res.setRates(rates);
		} else {
			String[] symbols = new String[needToFind.size()];
			for (int i = 0; i < symbols.length; i++) {
				symbols[i] = needToFind.get(i).getCurrencyName();
			}
			res = askFromApi("USD", symbols, needToFind.get(0).getDate(), needToFind.get(needToFind.size()).getDate());
		}
		return res;

	}

	@GetMapping("http://api.exchangeratesapi.io/history")
	public CurrencyResponseDTO askFromApi(@RequestParam String base, String[] symbols, String start_at, String end_at) {
		return new CurrencyResponseDTO();
	}

}
