package com.cfs.avaliacao4.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.cfs.avaliacao4.entity.enums.Ideology;

import lombok.Data;

@Data
public class PoliticalPartyFullDTO {

	private Integer id;
	private String name;
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	private LocalDate fundationDate;
	
	private List<AssociateDTO> associacoes = new ArrayList<>();
}
