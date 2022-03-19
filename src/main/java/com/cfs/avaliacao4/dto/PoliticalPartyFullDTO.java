package com.cfs.avaliacao4.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.cfs.avaliacao4.entity.Associate;
import com.cfs.avaliacao4.entity.enums.Ideology;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class PoliticalPartyFullDTO {

	private Integer id;
	private String name;
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fundationDate;
	
	private List<Associate> associates = new ArrayList<>();
}
