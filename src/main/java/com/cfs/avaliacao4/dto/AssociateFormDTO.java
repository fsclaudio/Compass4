package com.cfs.avaliacao4.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cfs.avaliacao4.entity.enums.Office;
import com.cfs.avaliacao4.entity.enums.Sexo;

import lombok.Data;

@Data
public class AssociateFormDTO {	
	@Size(min = 4, max = 150, message = "O nome deve conter de 5 a 150 caracteres")
	@NotBlank(message = "Campo Obrigatório")
	private String name;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Office office;
	@NotNull
	private LocalDate birthDate;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private Long politicalPartyId;

}
