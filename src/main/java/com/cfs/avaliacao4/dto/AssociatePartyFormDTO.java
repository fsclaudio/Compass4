package com.cfs.avaliacao4.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssociatePartyFormDTO {
	@NotNull
	private Integer idAssociado;
	@NotNull
	private Integer idPartido;

}
