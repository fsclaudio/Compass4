package com.cfs.avaliacao4.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.cfs.avaliacao4.entity.enums.Ideology;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class PoliticalPartyDTO {

	private Integer id;
	private String name;
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fundationDate;
}
