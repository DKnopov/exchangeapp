package com.knopov.exchangeapp.dto.helper;

import java.time.LocalDate;

import com.knopov.exchangeapp.dto.ResponceFromApiDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApiInterface {

	@GET("history/base=USD")
	Call<ResponceFromApiDTO> getCurrency(@Query("symbols") Iterable<String> symbols,
			@Query("start_at") LocalDate startAt, @Query("end_at") LocalDate endAt);
}
