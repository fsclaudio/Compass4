package com.cfs.avaliacao4.dto;

import java.time.Instant;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.cfs.avaliacao4.entity.enums.Ideology;

import lombok.Data;

@Data
public class PartiesDTO {

	private Integer id;
	private String name;
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	private Instant fundationDate;
}
