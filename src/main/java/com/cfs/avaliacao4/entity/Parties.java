package com.cfs.avaliacao4.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cfs.avaliacao4.entity.enums.Ideology;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant fundationDate;
	
}
