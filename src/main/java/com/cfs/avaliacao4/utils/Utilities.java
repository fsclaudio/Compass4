package com.cfs.avaliacao4.utils;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import com.cfs.avaliacao4.dto.PoliticalPartyFormDTO;

@Component
public class Utilities {
	
	public  int anos(final LocalDate data) {
		final LocalDate dataAtual = LocalDate.now();
		final Period periodo = Period.between(data, dataAtual);
		return periodo.getYears();
	}

	public boolean futereDate(PoliticalPartyFormDTO dto) {
		final LocalDate dataAtual = LocalDate.now();
		final LocalDate dataDigitado = dto.getFundationDate();
		if (dataAtual.isBefore(dataDigitado)) {
			return false;
		} else
			return true;
	}

	public boolean futereDate(LocalDate data) {
		final LocalDate dataAtual = LocalDate.now();
		final LocalDate dataDigitado = data;
		if (dataAtual.isBefore(dataDigitado)) {
			return false;
		} else
			return true;
	}

}
