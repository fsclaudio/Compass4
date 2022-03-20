package com.cfs.avaliacao4.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cfs.avaliacao4.entity.enums.Ideology;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoliticalParty implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String acronym;
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	private LocalDate fundationDate;
	
	@OneToMany(mappedBy = "politicalParty", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Associate> associates = new ArrayList<>();
	
}
