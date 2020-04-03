package com.knopov.exchangeapp.entity.helper;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;

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
@Embeddable
public class Keys implements Serializable {

	private static final long serialVersionUID = 1L;
	private String currencyName;
	private LocalDate date;

}