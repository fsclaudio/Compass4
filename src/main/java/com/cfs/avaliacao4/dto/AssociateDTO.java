package com.cfs.avaliacao4.dto;

import java.time.LocalDate;

import com.cfs.avaliacao4.entity.PoliticalParty;
import com.cfs.avaliacao4.entity.enums.Office;
import com.cfs.avaliacao4.entity.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class AssociateDTO {
	private Integer id;
	private String name;
	private Office office;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate birthDate;
	private Sexo sexo;
	
	private PoliticalParty politicalParty;
	
}
