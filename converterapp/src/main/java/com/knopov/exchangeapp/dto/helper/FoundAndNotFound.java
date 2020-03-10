package com.knopov.exchangeapp.dto.helper;

import java.util.List;

import com.knopov.exchangeapp.entity.Currency;

public class FoundAndNotFound {
	private List<Currency> found;
	private List<Currency> needToFind;
	
	public FoundAndNotFound() {
	}

	public FoundAndNotFound(List<Currency> found, List<Currency> needToFind) {
		this.found = found;
		this.needToFind = needToFind;
	}

	public List<Currency> getFound() {
		return found;
	}

	public void setFound(List<Currency> found) {
		this.found = found;
	}

	public List<Currency> getNeedToFind() {
		return needToFind;
	}

	public void setNeedToFind(List<Currency> needToFind) {
		this.needToFind = needToFind;
	}
	
	
	
	
}
