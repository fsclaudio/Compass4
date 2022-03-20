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
import com.cfs.avaliacao4.dto.AssociateDTO;
import com.cfs.avaliacao4.dto.AssociateFormDTO;
import com.cfs.avaliacao4.entity.Associate;
import com.cfs.avaliacao4.exceptions.ResourceNotFoundException;
import com.cfs.avaliacao4.repository.AssociateRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Associate Service")
class AssociateServiceTest {

	@Autowired
	private AssociateService service;

	@MockBean
	private AssociateRepository repository;

	@Test
	@DisplayName("Save Associate")
	void saveAssociate() {
		Associate associate = AssociateBuilder.getAssociate();

		when(this.repository.save(any(Associate.class))).thenReturn(associate);

		AssociateDTO associateDTO = this.service.save(AssociateBuilder.getAssociateFomDTO());

		assertThat(associateDTO.getId()).isNotNull();
		assertThat(associateDTO.getName()).isEqualTo(associate.getName());
		assertThat(associateDTO.getOffice()).isEqualTo(associate.getOffice());
		assertThat(associateDTO.getSexo()).isEqualTo(associate.getSexo());
		assertThat(associateDTO.getBirthDate()).isEqualTo(associate.getBirthDate());
	}

	@Test
	@DisplayName("Search Associate")
	void searchAssociate() {
		Associate associate = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyInt())).thenReturn(Optional.of(associate));

		AssociateDTO associateDTO = this.service.findById(associate.getId());

		assertThat(associateDTO.getId()).isNotNull();
		assertThat(associateDTO.getName()).isEqualTo(associate.getName());
		assertThat(associateDTO.getOffice()).isEqualTo(associate.getOffice());
		assertThat(associateDTO.getSexo()).isEqualTo(associate.getSexo());
		assertThat(associateDTO.getBirthDate()).isEqualTo(associate.getBirthDate());
	}

	@Test
	@DisplayName("Search Associate not found")
	void searchAssociateNotFound() {
		Associate associate = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

		assertThatExceptionOfType(ResourceNotFoundException.class)
				.isThrownBy(() -> this.service.findById(associate.getId()));

	}

	@Test
	@DisplayName("Update associate")
	public void updateAssociate() {
		Associate associate = AssociateBuilder.getAssociate();
		AssociateFormDTO associateFormDTO = AssociateBuilder.getAssociateFomDTO();
		associateFormDTO.setName("Associate Test update");

		when(this.repository.findById(anyInt())).thenReturn(Optional.of(associate));
		when(this.repository.save(any(Associate.class))).thenReturn(associate);

		AssociateDTO associateDTO = this.service.updateAssociate(associate.getId(), associateFormDTO);

		assertThat(associateDTO.getId()).isNotNull();
		assertThat(associateDTO.getName()).isEqualTo(associateFormDTO.getName());
		assertThat(associateDTO.getSexo()).isEqualTo(associateFormDTO.getSexo());
		assertThat(associateDTO.getBirthDate()).isEqualTo(associateFormDTO.getBirthDate());
	}

	@Test
	@DisplayName("Update associate not found")
	public void updateAssociateNotFound() {
		Associate associate = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

		assertThatExceptionOfType(ResourceNotFoundException.class)
				.isThrownBy(() -> this.service.findById(associate.getId()));
	}

	@Test
	@DisplayName("Delete associate")
	public void deleteAssociate() {
		Associate associate = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyInt())).thenReturn(Optional.of(associate));

		this.service.deleteAssociate(1);

		verify(this.repository, times(1)).delete(associate);
	}

	@Test
	@DisplayName("delte associate not found")
	public void deleteAssociateNotFound() {
		Associate associate = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

		assertThatExceptionOfType(ResourceNotFoundException.class)
				.isThrownBy(() -> this.service.deleteAssociate(associate.getId()));
	}

}
