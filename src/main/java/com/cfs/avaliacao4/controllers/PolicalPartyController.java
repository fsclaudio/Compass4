package com.cfs.avaliacao4.controllers;

import java.util.List;

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

import com.cfs.avaliacao4.dto.PoliticalPartyDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFormDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFullDTO;
import com.cfs.avaliacao4.entity.enums.Ideology;
import com.cfs.avaliacao4.services.PoliticalPartyService;

@RestController
@RequestMapping("/partidos")
public class PolicalPartyController {

	@Autowired
	PoliticalPartyService service;
	
	@GetMapping
	public ResponseEntity<Page<PoliticalPartyDTO>> listAllStates(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy

	) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage,
				org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);

		Page<PoliticalPartyDTO> list = service.listPoliticalParty(pageRequest);

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PoliticalPartyDTO> findById(@PathVariable Integer id) {
		PoliticalPartyDTO dto = service.findById(id);

		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/associados")
	public ResponseEntity<PoliticalPartyFullDTO> findByIdAssociado(@PathVariable Integer id) {
		PoliticalPartyFullDTO dto = service.findByIdAssociate(id);

		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "ideologias/{ideology}")
	public ResponseEntity<List<PoliticalPartyDTO>> findByIdeology(@PathVariable String ideology) {
		return   service.findByIdeology(Ideology.valueOf(ideology));
	}
	
	@PostMapping
	public ResponseEntity<PoliticalPartyDTO> save(@RequestBody @Valid PoliticalPartyFormDTO body){

		PoliticalPartyDTO state = service.saveState(body);
		return ResponseEntity.ok(state);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<PoliticalPartyDTO> update(@PathVariable Integer id, @RequestBody @Valid  PoliticalPartyFormDTO politicalPartyFormDTO) {
		PoliticalPartyDTO dto = service.findById(id);
		
		PoliticalPartyDTO state = service.updatePoliticalParty(dto.getId(), politicalPartyFormDTO);

		return ResponseEntity.ok().body(state);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PoliticalPartyDTO> delete(@PathVariable Integer id) {
		PoliticalPartyDTO dto = service.findById(id);
		service.deletePoliticalParty(dto.getId());

		return ResponseEntity.noContent().build();
	}
	
}
