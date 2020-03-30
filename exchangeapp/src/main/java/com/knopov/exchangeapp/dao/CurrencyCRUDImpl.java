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

import com.knopov.exchangeapp.dto.CurrencyQueryDTO;
import com.knopov.exchangeapp.dto.CurrencyResponseDTO;
import com.knopov.exchangeapp.dto.ResponceFromApiDTO;
import com.knopov.exchangeapp.dto.helper.CurrencyApiInterface;
import com.knopov.exchangeapp.dto.helper.CustomCurrencies;
import com.knopov.exchangeapp.dto.helper.Rate;
import com.knopov.exchangeapp.entity.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
		LocalDate from = LocalDate.parse(order.getStartAt());
		LocalDate to = LocalDate.parse(order.getEndAt());
		List<Currency> currencies;

		List<Currency> found = new ArrayList<Currency>();
		List<Currency> needToFind = new ArrayList<Currency>();

		CurrencyResponseDTO res = new CurrencyResponseDTO();
		Rate rate = new Rate();
		List<Rate> rates = new ArrayList<>();
		CustomCurrencies custCur = new CustomCurrencies();
		Map<String, Double> curMap = new HashMap<>();
		Map<LocalDate, CustomCurrencies> datesAndCurrencies = new HashMap<>();

		for (String currencyName : symbols) {

			theQuery = entityManager.createQuery(
					"FROM Currency WHERE currency_name = :currencyName AND ( date_needed BETWEEN :from AND :to )",
					Currency.class);

			theQuery.setParameter("currencyName", currencyName).setParameter("from", from).setParameter("to", to);
			currencies = (List<Currency>) theQuery.getResultList();

			if (currencies.equals(null) || currencies.isEmpty()) {
				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					Currency cur = new Currency(currencyName, i, 0);
					needToFind.add(cur);
				}
			} else {
				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					int j = 0;
					Currency thisCur = currencies.get(j);
					LocalDate date = thisCur.getDate();
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
				custCur.setCustomCurrencies(curMap);
				datesAndCurrencies.put(currency.getDate(), custCur);
				rate.setDatesAndCurrencies(datesAndCurrencies);
				rates.add(rate);
			}
			res.setRates(rates);
		} else {

			Set<String> symbs = new HashSet<>();
			needToFind.forEach(System.out::println);

			needToFind.forEach(a -> symbs.add(a.getCurrencyName()));
			symbs.forEach(System.out::println);
			Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io/")
					.addConverterFactory(GsonConverterFactory.create()).build();
			CurrencyApiInterface currencyInterface = retrofit.create(CurrencyApiInterface.class);
			Call<ResponceFromApiDTO> call = currencyInterface.getCurrency(symbs, needToFind.get(0).getDate(),
					needToFind.get(needToFind.size() - 1).getDate());
			Call<ResponceFromApiDTO> call1 = currencyInterface.getCurrency(symbs, LocalDate.parse("2020-10-10"),
					LocalDate.parse("2020-10-25"));
			call1.enqueue(new Callback<ResponceFromApiDTO>() {

				@Override
				public void onResponse(Call<ResponceFromApiDTO> call, Response<ResponceFromApiDTO> response) {

					System.out.println(call.isExecuted());
					ResponceFromApiDTO resp = response.body();
					System.out.println(response.isSuccessful());
					System.out.println(resp);
					// list.forEach(System.out::println);
				}

				@Override
				public void onFailure(Call<ResponceFromApiDTO> call, Throwable t) {
					// TODO Auto-generated method stub

				}
			});
		}

		return null;
	}

	@Override
	public Currency addFewCurrenciesManually(Currency cur) {
		entityManager.persist(cur);
		return cur;
	}

}
