package com.cfs.avaliacao4.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cfs.avaliacao4.dto.PoliticalPartyDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFormDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFullDTO;
import com.cfs.avaliacao4.entity.PoliticalParty;
import com.cfs.avaliacao4.entity.enums.Ideology;
import com.cfs.avaliacao4.exceptions.ResourceNotFoundException;
import com.cfs.avaliacao4.repository.PoliticalPartyRepository;
import com.cfs.avaliacao4.utils.Utilities;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {
	
	@Autowired
	private Utilities util;
	
	@Autowired
	private PoliticalPartyRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	private PoliticalParty party;
	@Override
	public PoliticalPartyDTO saveState(PoliticalPartyFormDTO body) {
		if (!util.futereDate(body)) {
			throw new ResourceNotFoundException("Data de Fundação não pode ser futura");
		}
		return mapper.map(repository.save(mapper.map(body, PoliticalParty.class)),PoliticalPartyDTO.class);
	}


	@Override
	public Page<PoliticalPartyDTO> listPoliticalParty(PageRequest pageRequest) {
            Page<PoliticalParty> page= repository.findAll(pageRequest);
           
            List<PoliticalPartyDTO> list = page.getContent().stream().map(PoliticalParty -> mapper.map(PoliticalParty, PoliticalPartyDTO.class)).collect(Collectors.toList());
    		return new PageImpl<>(list);
		  
	}

	@Override
	public PoliticalPartyDTO updatePoliticalParty(Integer id, PoliticalPartyFormDTO body) {
		if (!util.futereDate(body)) {
			throw new ResourceNotFoundException("Data de Fundação não pode ser futura");
		}
		Optional<PoliticalParty> party = repository.findById(id);
		party.get().setName(body.getName());
		party.get().setIdeology(body.getIdeology());
		party.get().setAcronym(body.getAcronym());
		party.get().setFundationDate(body.getFundationDate());
		return mapper.map(repository.save(party.get()),PoliticalPartyDTO.class);
	}

	@Override
	public PoliticalPartyDTO findById(Integer id) {
		Optional<PoliticalParty> state = repository.findById(id);
		party = state.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		return mapper.map(state.get(), PoliticalPartyDTO.class);
	}
	
	@Override
	public ResponseEntity <List<PoliticalPartyDTO>> findByIdeology(Ideology ideology) {
		List<PoliticalParty> entity = repository.findByIdeologyEguals(ideology);
		List<PoliticalPartyDTO>  list = Arrays.asList(mapper.map(entity, PoliticalPartyDTO[].class));	
		return ResponseEntity.ok(list);
	}

	@Override
	public void deletePoliticalParty(Integer id) {
		Optional<PoliticalParty> state = repository.findById(id);
		party = state.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		repository.delete(state.get());
		
	}


	@Override
	public PoliticalPartyFullDTO findByIdAssociate(Integer id) {
		PoliticalParty state = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		return mapper.map(state, PoliticalPartyFullDTO.class);
	}

}
