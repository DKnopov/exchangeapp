package com.knopov.exchangeapp.dto;

import java.util.List;

public class CurrencyQueryDTO {
	private List<String> symbols;
	private String startAt;
	private String endAt;
	
	public CurrencyQueryDTO() {
		
	}

	public CurrencyQueryDTO(List<String> symbols, String startAt, String endAt) {
		this.symbols = symbols;
		this.startAt = startAt;
		this.endAt = endAt;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}

	@Override
	public String toString() {
		return "CurrencyQueryDTO [symbols=" + symbols + ", startAt=" + startAt + ", endAt=" + endAt + "]";
	}
	
	
	
	
}
