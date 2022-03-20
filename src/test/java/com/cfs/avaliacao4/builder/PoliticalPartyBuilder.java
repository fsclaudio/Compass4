package com.cfs.avaliacao4.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.cfs.avaliacao4.dto.PoliticalPartyDTO;
import com.cfs.avaliacao4.dto.PoliticalPartyFormDTO;
import com.cfs.avaliacao4.entity.PoliticalParty;
import com.cfs.avaliacao4.entity.enums.Ideology;

public class PoliticalPartyBuilder {

	public static PoliticalParty getParty() {
		PoliticalParty party = new PoliticalParty();
		party.setId(1);
		party.setName("Partido Social Brasileiro");
		party.setIdeology(Ideology.CENTRO);
		party.setAcronym("PSB");
		party.setFundationDate(LocalDate.of(2000, 8, 22));
		return party;
		
	}
	
	public static PoliticalPartyDTO getPolicalPartyDTO() {
		PoliticalPartyDTO party = new PoliticalPartyDTO();
		party.setId(1);
		party.setName("Partido Social Brasileiro");
		party.setIdeology(Ideology.CENTRO);
		party.setAcronym("PSB");
		party.setFundationDate(LocalDate.of(2000, 8, 22));
		return party;	
	}
	
	public static PoliticalPartyFormDTO getPoliticalPartyFomDTO() {
		PoliticalPartyFormDTO party = new PoliticalPartyFormDTO();
		party.setName("Partido Social Brasileiro");
		party.setIdeology(Ideology.CENTRO);
		party.setAcronym("PSB");
		party.setFundationDate(LocalDate.of(2000, 8, 22));
		return party;		
	}
	
	public static Page<PoliticalPartyDTO> listPolicalParty(){
		PoliticalPartyDTO party = new PoliticalPartyDTO();
		party.setName("Partido Social Brasileiro");
		party.setIdeology(Ideology.CENTRO);
		party.setAcronym("PSB");
		party.setFundationDate(LocalDate.of(2000, 8, 22));
		
		List<PoliticalPartyDTO> parties = new ArrayList<>();
		parties.add(party);
		
		return new PageImpl<>(parties, PageRequest.of(0,1),1);
	}
	
	    public static Page<PoliticalParty> associates() {
	        return new PageImpl<>(Arrays.asList(PoliticalPartyBuilder.getParty()), PageRequest.of(0, 10), 1);
	    }

	    public static Page<PoliticalPartyDTO> associatesDTO() {
	        return new PageImpl<>(Arrays.asList(PoliticalPartyBuilder.getPolicalPartyDTO()), PageRequest.of(0, 10), 1);
	    }
}
