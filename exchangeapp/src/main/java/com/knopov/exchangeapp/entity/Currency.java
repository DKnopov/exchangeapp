package com.knopov.exchangeapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.knopov.exchangeapp.entity.helper.Keys;

@Entity
@Table(name = "main")
@IdClass(Keys.class)
public class Currency {

	@Id
	@Column(name = "currency_name")
	private String currencyName;

	@Id
	@Column(name = "date_needed")
	//@Convert(converter = LocalDateConverter.class)
	private LocalDate date;

	@Column(name = "value_cur")
	private double value;

	public Currency() {

	}

	public Currency(String currencyName, LocalDate date, double value) {
		this.currencyName = currencyName;
		this.date = date;
		this.value = value;
	}
	
	

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Currency [currencyName=" + currencyName + ", date=" + date + ", value=" + value + "]";
	}
	
	

}
