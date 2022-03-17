package com.cfs.avaliacao4.dto;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cfs.avaliacao4.entity.enums.Ideology;

import lombok.Data;

@Data
public class PartiesFormDTO {
	
	@Size(min = 4, max = 150, message = "O nome deve conter de 5 a 150 caracteres")
	@NotBlank(message = "Campo Obrigatório")
	private String name;
	@Size(min = 2, max = 10, message = "Silga deve conter de 2 a 10 caracteres")
	@NotBlank(message = "Campo Obrigatório")
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@NotBlank(message = "Campo Obrigatório")
	private Instant fundationDate;

}
