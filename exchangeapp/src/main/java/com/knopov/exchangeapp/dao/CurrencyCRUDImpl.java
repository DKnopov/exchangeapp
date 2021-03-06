package com.knopov.exchangeapp.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;
import com.knopov.exchangeapp.entity.Currency;

@Repository
public class CurrencyCRUDImpl implements CurrencyCRUD {

	private EntityManager entityManager;

	@Autowired
	public CurrencyCRUDImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CurrencyResponseDTO getCurrencies(CurrencyQueryDTO order) {
		Query theQuery;
		List<String> symbols = order.getSymbols();
		LocalDate from = order.getStartAt();
		LocalDate to = order.getEndAt().plusDays(1);
		List<Currency> currencies;

		List<Currency> found = new ArrayList<Currency>();
		List<Currency> needToFind = new ArrayList<Currency>();

		CurrencyResponseDTO res = new CurrencyResponseDTO();
		Map<LocalDate, Map<String, Double>> rate = new HashMap<LocalDate, Map<String, Double>>();
		Map<String, Double> curMap = new HashMap<>();

		for (String currencyName : symbols) {

			theQuery = entityManager.createQuery(
					"FROM Currency WHERE currency_name = :currencyName AND ( date_needed BETWEEN :from AND :to )"
							+ "ORDER BY date_needed",
					Currency.class);

			theQuery.setParameter("currencyName", currencyName).setParameter("from", from).setParameter("to", to);
			currencies = (List<Currency>) theQuery.getResultList();
			
			if (currencies.equals(null) || currencies.isEmpty()) {
				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					Currency cur = new Currency(currencyName, i, 0);
					needToFind.add(cur);
				}
			} else {
				Currency thisCur;
				LocalDate date;
				int j = 0;
				for (LocalDate i = from; j < currencies.size(); i = i.plusDays(1)) {
					thisCur = currencies.get(j);
					date = thisCur.getDate();
					if (date.equals(i)) {
						found.add(thisCur);
						j++;
					} else {
						needToFind.add(new Currency(thisCur.getCurrencyName(), i, 0));
					}
				}
			}
		}

		if (needToFind.isEmpty()) {
			for (Currency currency : found) {
				curMap.put(currency.getCurrencyName(), currency.getValue());
				rate.put(currency.getDate(), curMap);
			}
			res.setRates(rate);
		} else {

			Set<String> symbs = new HashSet<>();

			needToFind.forEach(a -> symbs.add(a.getCurrencyName()));

			String ourUrl = "https://api.exchangeratesapi.io/history";
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ourUrl).queryParam("symbols", symbs)
					.queryParam("start_at", needToFind.get(0).getDate())
					.queryParam("end_at", needToFind.get(needToFind.size() - 1).getDate()).queryParam("base", "USD");
			RestTemplate restTemplate = new RestTemplate();
			CurrencyResponseDTO response = restTemplate.getForObject(builder.toUriString(), CurrencyResponseDTO.class);
			addFewCurrenciesToDb(response, found);
			response.addMoreCurrencies(found);
			res = response.createWithErrorDTO(needToFind);
		}

		return res;
	}

	public void addFewCurrenciesToDb(CurrencyResponseDTO given, List<Currency> found) {
		Currency cur;
		Map<LocalDate, Map<String, Double>> rates = given.getRates();
		for (Map.Entry<LocalDate, Map<String, Double>> entry : rates.entrySet()) {
			for (Map.Entry<String, Double> innerEntry : entry.getValue().entrySet()) {
				cur = new Currency(innerEntry.getKey(), entry.getKey(), innerEntry.getValue());
				if (!found.contains(cur)) {
					entityManager.merge(cur);
				}
			}
		}
	}

}
