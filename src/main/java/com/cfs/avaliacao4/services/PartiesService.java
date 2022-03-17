package com.cfs.avaliacao4.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cfs.avaliacao4.dto.PartiesDTO;
import com.cfs.avaliacao4.dto.PartiesFormDTO;

public interface PartiesService {

    PartiesDTO saveState(PartiesFormDTO body);
	
	Page<PartiesDTO> listStates();
	
	Page<PartiesDTO> listStates(PageRequest pageRequest);
	
	PartiesDTO updateState(Integer id, PartiesFormDTO body);
	
	PartiesDTO findById(Integer id);
	
	void deleteState(Integer id);
}
