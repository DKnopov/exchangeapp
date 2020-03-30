package com.knopov.exchangeapp.dto;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;
import com.knopov.exchangeapp.dto.helper.Rate;

public class ResponceFromApiDTO {

	@SerializedName ("rates")
	private Rate rates;
	@SerializedName ("start_at")
	private LocalDate startAt;
	@SerializedName ("base")
	private String base;
	@SerializedName("end_at")
	private LocalDate endAt;
	
	public ResponceFromApiDTO() {
	}

	

	public ResponceFromApiDTO(Rate rates, LocalDate start_at, String base, LocalDate end_at) {
		this.rates = rates;
		this.startAt = start_at;
		this.base = base;
		this.endAt = end_at;
	}



	public Rate getRates() {
		return rates;
	}

	public void setRates(Rate rates) {
		this.rates = rates;
	}

	public LocalDate getStart_at() {
		return startAt;
	}

	public void setStart_at(LocalDate start_at) {
		this.startAt = start_at;
	}

	public LocalDate getEnd_at() {
		return endAt;
	}

	public void setEnd_at(LocalDate end_at) {
		this.endAt = end_at;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}



	@Override
	public String toString() {
		return "ResponceFromApiDTO [rates=" + rates + ", start_at=" + startAt + ", base=" + base + ", end_at=" + endAt
				+ "]";
	}
	
	
	
	
	
}
