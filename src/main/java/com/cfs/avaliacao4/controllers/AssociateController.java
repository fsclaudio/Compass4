package com.cfs.avaliacao4.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cfs.avaliacao4.dto.AssociateDTO;
import com.cfs.avaliacao4.dto.AssociateFormDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyDTO;
import com.cfs.avaliacao4.services.AssociateService;
import com.cfs.avaliacao4.services.PoliticalPartyService;

@RestController
@RequestMapping("/associados")
public class AssociateController {

	@Autowired
	AssociateService service;
	
	@Autowired
	PoliticalPartyService partyService;
	
	@GetMapping
	public ResponseEntity<Page<AssociateDTO>> listAllStates(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy

	) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage,
				org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);

		Page<AssociateDTO> list = service.listAssociate(pageRequest);

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AssociateDTO> findById(@PathVariable Integer id) {
		AssociateDTO dto = service.findById(id);

		return ResponseEntity.ok().body(dto);
	}
	
//	@GetMapping(value = "ideologias/{ideology}")
//	public ResponseEntity<List<AssociateDTO>> findByIdeology(@PathVariable String ideology) {
//		return   service.findByIdeology(Ideology.valueOf(ideology));
//	}
	
	@PostMapping
	public ResponseEntity<AssociateDTO> save(@RequestBody @Valid AssociateFormDTO body){

		AssociateDTO state = service.save(body);
		return ResponseEntity.ok(state);
	}
	
//	@PostMapping(value = "/partidos")
//	public ResponseEntity<AssociateDTO> saveAssociate(@RequestBody Integer associateId, Integer partyId){
//
//		AssociateDTO associate = service.findById(associateId);
//		PoliticalPartyDTO party = partyService.findById(partyId);
//		
//		
//		associate.getPoliticalParty().getAssociates().add(party);
//		return ResponseEntity.ok(associate);
//	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AssociateDTO> update(@PathVariable Integer id, @RequestBody @Valid  AssociateFormDTO associateFormdto) {
		AssociateDTO dto = service.findById(id);
		
		AssociateDTO state = service.updateAssociate(dto.getId(), associateFormdto);

		return ResponseEntity.ok().body(state);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AssociateDTO> delete(@PathVariable Integer id) {
		AssociateDTO dto = service.findById(id);
		service.deleteAssociate(dto.getId());

		return ResponseEntity.noContent().build();
	}
	
}
