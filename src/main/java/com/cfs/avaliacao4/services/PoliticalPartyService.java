package com.cfs.avaliacao4.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import com.cfs.avaliacao4.dto.PoliticalPartyDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFormDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFullDTO;
import com.cfs.avaliacao4.entity.enums.Ideology;

public interface PoliticalPartyService {

    PoliticalPartyDTO saveState(PoliticalPartyFormDTO body);
	
	Page<PoliticalPartyDTO> listPoliticalParty(PageRequest pageRequest);
	
	PoliticalPartyDTO updatePoliticalParty(Integer id, PoliticalPartyFormDTO body);
	
	PoliticalPartyDTO findById(Integer id);
	
	PoliticalPartyFullDTO findByIdAssociate(Integer id);
	
	ResponseEntity<List<PoliticalPartyDTO>> findByIdeology(Ideology ideology);
	
	void deletePoliticalParty(Integer id);
}
