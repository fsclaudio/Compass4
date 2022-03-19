package com.cfs.avaliacao4.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import com.cfs.avaliacao4.dto.AssociateDTO;
import com.cfs.avaliacao4.dto.AssociateFormDTO;
import com.cfs.avaliacao4.entity.enums.Office;

public interface AssociateService {

    AssociateDTO save(AssociateFormDTO body);
	
	Page<AssociateDTO> listAssociate(PageRequest pageRequest);
	
	AssociateDTO updateAssociate(Integer id, AssociateFormDTO body);
	
	AssociateDTO findById(Integer id);

	
	ResponseEntity<List<AssociateDTO>> findByOffice(Office office);
	
	void deleteAssociate(Integer id);
}
