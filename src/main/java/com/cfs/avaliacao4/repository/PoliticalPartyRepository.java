package com.cfs.avaliacao4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cfs.avaliacao4.entity.PoliticalParty;
import com.cfs.avaliacao4.entity.enums.Ideology;


public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer> {

	@Query("SELECT DISTINCT obj FROM PoliticalParty obj  " + " WHERE  obj.ideology in(:ideology)  ")
	List<PoliticalParty> findByIdeologyEguals(Ideology ideology);
}
