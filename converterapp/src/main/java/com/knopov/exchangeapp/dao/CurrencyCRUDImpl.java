package com.knopov.exchangeapp.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.helper.FoundAndNotFound;
import com.knopov.exchangeapp.entity.Currency;

@Repository
public class CurrencyCRUDImpl implements CurrencyCRUD {

	private EntityManager entityManager;

	@Autowired
	public CurrencyCRUDImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public FoundAndNotFound transitionalResult(CurrencyQueryDTO order) {
		Query theQuery;
		List<String> symbols = order.getSymbols();
		LocalDate from = LocalDate.parse(order.getStartAt());
		LocalDate to = LocalDate.parse(order.getEndAt());
		List<Currency> currencies;

		List<Currency> found = new ArrayList<Currency>();
		List<Currency> needToFind = new ArrayList<Currency>();

		for (String currencyName : symbols) {
			theQuery = entityManager.createQuery(
					"SELECT * FROM public.main WHERE currency_name = :currencyName AND ( date BETWEEN :from AND :to )",
					Currency.class);
			currencies = theQuery.getResultList();
			if (currencies == null) {
				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					Currency cur = new Currency(currencyName, i.toString(), 0);
					needToFind.add(cur);
				}
			} else {
				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					int j = 0;
					Currency thisCur = currencies.get(j);
					LocalDate date = LocalDate.parse(thisCur.getDate());
					if (date.equals(i)) {
						found.add(thisCur);
						j++;
					} else {
						needToFind.add(new Currency(thisCur.getCurrencyName(), i.toString(), 0));
					}
				}
			}
		}
		FoundAndNotFound foundAndNotFound = new FoundAndNotFound(found, needToFind);

		return foundAndNotFound;
	}

}
