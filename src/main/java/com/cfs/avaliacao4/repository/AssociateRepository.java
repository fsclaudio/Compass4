package com.cfs.avaliacao4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cfs.avaliacao4.entity.Associate;
import com.cfs.avaliacao4.entity.enums.Office;


public interface AssociateRepository extends JpaRepository<Associate, Integer> {

	@Query("SELECT DISTINCT obj FROM Associate obj  " + " WHERE  obj.office in(:office)  ")
	List<Associate> findByOfficeEguals(Office office);
	
	@Query("SELECT obj FROM Associate obj JOIN FETCH obj.politicalParty")
	List<Associate> findAssociatesWithAssociate();
}
