package com.cfs.avaliacao4.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cfs.avaliacao4.builder.AssociateBuilder;
import com.cfs.avaliacao4.builder.PoliticalPartyBuilder;
import com.cfs.avaliacao4.dto.PoliticalPartyDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFormDTO;
import com.cfs.avaliacao4.entity.Associate;
import com.cfs.avaliacao4.entity.PoliticalParty;
import com.cfs.avaliacao4.exceptions.ResourceNotFoundException;
import com.cfs.avaliacao4.repository.PoliticalPartyRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Tests for PoliticalParty Service")
class PoliticalPartyServiceTest {

	@Autowired
	private PoliticalPartyService service;

	@MockBean
	private PoliticalPartyRepository repository;
	
	@Test
	@DisplayName("Save PoliticalParty")
	void savePoliticalParty() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();

		when(this.repository.save(any(PoliticalParty.class))).thenReturn(party);

		PoliticalPartyDTO PoliticalPartyDTO = this.service.saveState(PoliticalPartyBuilder.getPoliticalPartyFomDTO());

		assertThat(PoliticalPartyDTO.getId()).isNotNull();
		assertThat(PoliticalPartyDTO.getName()).isEqualTo(party.getName());
		assertThat(PoliticalPartyDTO.getAcronym()).isEqualTo(party.getAcronym());
		assertThat(PoliticalPartyDTO.getFundationDate()).isEqualTo(party.getFundationDate());
		assertThat(PoliticalPartyDTO.getIdeology()).isEqualTo(party.getIdeology());
	}

	@Test
	@DisplayName("Search PoliticalParty")
	void searchPolicalParte() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();
		
		when(this.repository.findById(anyInt())).thenReturn(Optional.of(party));

		PoliticalPartyDTO PoliticalPartyDTO = this.service.findById(party.getId());

		assertThat(PoliticalPartyDTO.getId()).isNotNull();
		assertThat(PoliticalPartyDTO.getName()).isEqualTo(party.getName());
		assertThat(PoliticalPartyDTO.getAcronym()).isEqualTo(party.getAcronym());
		assertThat(PoliticalPartyDTO.getFundationDate()).isEqualTo(party.getFundationDate());
		assertThat(PoliticalPartyDTO.getIdeology()).isEqualTo(party.getIdeology());
	}
	
	@Test
	@DisplayName("Search PoliticalParty not found")
	void searchPolicitaclaParty() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();

		when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

		assertThatExceptionOfType(ResourceNotFoundException.class)
				.isThrownBy(() -> this.service.findById(party.getId()));

	}
	
	@Test
	@DisplayName("Update PoliticalParty")
	public void updatePoliticalParty() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();
		
		PoliticalPartyFormDTO politicalPartyFormDTO = PoliticalPartyBuilder.getPoliticalPartyFomDTO();
		politicalPartyFormDTO.setName("PoliticalParty Test update");

		when(this.repository.findById(anyInt())).thenReturn(Optional.of(party));
		when(this.repository.save(any(PoliticalParty.class))).thenReturn(party);

		PoliticalPartyDTO PartyDTO = this.service.updatePoliticalParty(party.getId(), politicalPartyFormDTO);

		assertThat(PartyDTO.getId()).isNotNull();
		assertThat(PartyDTO.getName()).isEqualTo(politicalPartyFormDTO.getName());
		assertThat(PartyDTO.getAcronym()).isEqualTo(politicalPartyFormDTO.getAcronym());
		assertThat(PartyDTO.getFundationDate()).isEqualTo(politicalPartyFormDTO.getFundationDate());
		assertThat(PartyDTO.getIdeology()).isEqualTo(politicalPartyFormDTO.getIdeology());
	}
	
	@Test
	@DisplayName("Update politicalParty not found")
	public void updateAssociateNotFound() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();

		when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

		assertThatExceptionOfType(ResourceNotFoundException.class)
				.isThrownBy(() -> this.service.findById(party.getId()));
	}
	
	@Test
	@DisplayName("Delete politicalParty")
	public void deleteAssociate() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();

		when(this.repository.findById(anyInt())).thenReturn(Optional.of(party));

		this.service.deletePoliticalParty(1);

		verify(this.repository, times(1)).delete(party);
	}

	@Test
	@DisplayName("delte politicalParty not found")
	public void deleteAssociateNotFound() {
		PoliticalParty party = PoliticalPartyBuilder.getParty();

		when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

		assertThatExceptionOfType(ResourceNotFoundException.class)
				.isThrownBy(() -> this.service.deletePoliticalParty(party.getId()));
	}


}
