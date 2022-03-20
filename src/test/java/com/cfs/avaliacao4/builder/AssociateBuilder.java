package com.cfs.avaliacao4.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.cfs.avaliacao4.dto.AssociateDTO;
import com.cfs.avaliacao4.dto.AssociateFormDTO;
import com.cfs.avaliacao4.dto.AssociatePartyFormDTO;
import com.cfs.avaliacao4.entity.Associate;
import com.cfs.avaliacao4.entity.enums.Office;
import com.cfs.avaliacao4.entity.enums.Sexo;

public class AssociateBuilder {

	public static Associate getAssociate() {
		Associate associate = new Associate();
		associate.setId(1);
		associate.setName("Paulo");
		associate.setSexo(Sexo.MASCULINO);
		associate.setOffice(Office.DEPUTADO_FEDERAL);
		associate.setBirthDate(LocalDate.of(2000, 8, 22));
		return associate;
		
	}
	
	public static AssociateDTO getAssociateDTO() {
		AssociateDTO associate = new AssociateDTO();
		associate.setId(1);
		associate.setName("Paulo");
		associate.setSexo(Sexo.MASCULINO);
		associate.setOffice(Office.DEPUTADO_FEDERAL);
		associate.setBirthDate(LocalDate.of(2000, 8, 22));
		return associate;	
	}
	
	public static AssociatePartyFormDTO getAssociatePartyFormDTO() {
		AssociatePartyFormDTO associate = new AssociatePartyFormDTO();
		associate.setIdAssociado(1);
		associate.setIdPartido(1);
		return associate;	
	}
	
	public static AssociateFormDTO getAssociateFomDTO() {
		AssociateFormDTO associate = new AssociateFormDTO();
		associate.setName("Paulo");
		associate.setSexo(Sexo.MASCULINO);
		associate.setOffice(Office.DEPUTADO_FEDERAL);
		associate.setBirthDate(LocalDate.of(2000, 8, 22));
		return associate;	
	}
	
	public static Page<AssociateDTO> listAssociates(){
		AssociateDTO associate = new AssociateDTO();
		associate.setId(1);
		associate.setName("Paulo");
		associate.setSexo(Sexo.MASCULINO);
		associate.setOffice(Office.DEPUTADO_FEDERAL);
		associate.setBirthDate(LocalDate.of(2000, 8, 22));
		
		List<AssociateDTO> associates = new ArrayList<>();
		associates.add(associate);
		
		return new PageImpl<>(associates, PageRequest.of(0,1),1);
	}
	
	    public static Page<Associate> associates() {
	        return new PageImpl<>(Arrays.asList(AssociateBuilder.getAssociate()), PageRequest.of(0, 10), 1);
	    }

	    public static Page<AssociateDTO> associatesDTO() {
	        return new PageImpl<>(Arrays.asList(AssociateBuilder.getAssociateDTO()), PageRequest.of(0, 10), 1);
	    }
}
