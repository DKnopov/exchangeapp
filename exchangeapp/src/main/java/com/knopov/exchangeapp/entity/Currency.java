package com.knopov.exchangeapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.knopov.exchangeapp.entity.helper.Keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "main")
@IdClass(Keys.class)
public class Currency {

	@Id
	@Column(name = "currency_name")
	private String currencyName;

	@Id
	@Column(name = "date_needed")
	private LocalDate date;

	@Column(name = "value_cur")
	private double value;

}
