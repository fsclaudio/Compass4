package com.cfs.avaliacao4.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cfs.avaliacao4.entity.enums.Office;
import com.cfs.avaliacao4.entity.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class associate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Office office;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;
	private Sexo sexo;

}
