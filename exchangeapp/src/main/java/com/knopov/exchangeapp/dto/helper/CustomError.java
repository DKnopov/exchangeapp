package com.knopov.exchangeapp.dto.helper;

import java.util.Map;

public class CustomError {
	
	private Map<String, String> errors;

	public CustomError() {
	}

	public CustomError(Map<String, String> errors) {
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "CustomError [errors=" + errors + "]";
	}
	
	

}
