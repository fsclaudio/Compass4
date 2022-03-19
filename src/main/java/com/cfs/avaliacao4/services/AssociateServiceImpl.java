package com.cfs.avaliacao4.services;

import java.time.LocalDate;
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

import com.cfs.avaliacao4.dto.AssociateDTO;
import com.cfs.avaliacao4.dto.AssociateFormDTO;
import com.cfs.avaliacao4.dto.AssociatePartyFormDTO;
import com.cfs.avaliacao4.entity.Associate;
import com.cfs.avaliacao4.entity.PoliticalParty;
import com.cfs.avaliacao4.entity.enums.Office;
import com.cfs.avaliacao4.exceptions.ResourceNotFoundException;
import com.cfs.avaliacao4.repository.AssociateRepository;
import com.cfs.avaliacao4.repository.PoliticalPartyRepository;
import com.cfs.avaliacao4.utils.Utilities;

@Service
public class AssociateServiceImpl implements AssociateService {
	
	@Autowired
	private Utilities util;
	
	@Autowired
	private AssociateRepository repository;
	
	@Autowired
	private PoliticalPartyRepository partyRepository;
	
	
	@Autowired
	private ModelMapper mapper;

	private Associate associate;
	
	private PoliticalParty politicalParty;
	
	@Override
	public AssociateDTO save(AssociateFormDTO body) {
		LocalDate data = body.getBirthDate();
		if (!util.futereDate(data)) {
			throw new ResourceNotFoundException("Data de Nascimento não pode ser futura");
		}
		return mapper.map(repository.save(mapper.map(body, Associate.class)),AssociateDTO.class);
	}


	@Override
	public Page<AssociateDTO> listAssociate(PageRequest pageRequest) {
            Page<Associate> page= repository.findAll(pageRequest);
           
            List<AssociateDTO> list = page.getContent().stream().map(Associate -> mapper.map(Associate, AssociateDTO.class)).collect(Collectors.toList());
    		return new PageImpl<>(list);
		  
	}

	@Override
	public AssociateDTO updateAssociate(Integer id, AssociateFormDTO body) {
		LocalDate data = body.getBirthDate();
		if (!util.futereDate(data)) {
			throw new ResourceNotFoundException("Data de Nascimento não pode ser futura");
		}
		Optional<Associate> party = repository.findById(id);
		associate = party.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		party.get().setName(body.getName());
		party.get().setOffice(body.getOffice());
		party.get().setBirthDate(body.getBirthDate());
		party.get().setSexo(body.getSexo());
		return mapper.map(repository.save(party.get()),AssociateDTO.class);
	}

	@Override
	public AssociateDTO findById(Integer id) {
		Optional<Associate> state = repository.findById(id);
		associate = state.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		return mapper.map(state.get(), AssociateDTO.class);
	}
	
	@Override
	public ResponseEntity <List<AssociateDTO>> findByOffice(Office office) {
		List<Associate> entity = repository.findByOfficeEguals(office);
		List<AssociateDTO>  list = Arrays.asList(mapper.map(entity, AssociateDTO[].class));	
		return ResponseEntity.ok(list);
	}

	@Override
	public void deleteAssociate(Integer id) {
		Optional<Associate> state = repository.findById(id);
		associate = state.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		repository.delete(state.get());
		
	}


	@Override
	public AssociateDTO updateAssociateParty(AssociatePartyFormDTO form) {
	
		PoliticalParty partido = partyRepository.findById(form.getIdPartido())
				.orElseThrow(()-> new ResourceNotFoundException("Not Found Party"));
		Optional<Associate> entity = repository.findById(form.getIdAssociado());
		associate = entity.orElseThrow(()-> new ResourceNotFoundException("Not Found Associate"));
		
		entity.get().setPoliticalParty(partido);
		System.out.println(entity.get().getPoliticalParty().getId());
		return mapper.map(repository.save(entity.get()),AssociateDTO.class);
	}


	@Override
	public void deleteAssociateParty(Integer idPartido, Integer idAssociate) {
		Associate associate = repository.findById(idAssociate)
				.orElseThrow(()-> new ResourceNotFoundException("Associado não enconctrado " + idAssociate));
		PoliticalParty party = partyRepository.findById(idPartido)
				.orElseThrow(()-> new ResourceNotFoundException("Partido não enconctrado " + idPartido));
		
		associate.setPoliticalParty(null);
		
		associate= repository.save(associate);
	}

}