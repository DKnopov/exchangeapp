package com.knopov.exchangeapp.dto;

import java.time.LocalDate;
import java.util.List;

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
public class CurrencyQueryDTO {
	
	private List<String> symbols;
	private LocalDate startAt;
	private LocalDate endAt;

	
}
