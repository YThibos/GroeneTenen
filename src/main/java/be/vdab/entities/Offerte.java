package be.vdab.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.enums.Gazontype;

public class Offerte implements Serializable {

	
	// VALIDATION GROUPS
	public interface Stap1 {}
	public interface Stap2 {}
	
	private static final long serialVersionUID = 1L;
	
	
	// MEMBER VARIABLES
	@NotBlank(groups = Stap1.class)
	@SafeHtml(groups = Stap1.class)
	private String voornaam;
	@NotBlank(groups = Stap1.class)
	@SafeHtml(groups = Stap1.class)
	private String familienaam;
	@NotBlank(groups = Stap1.class)
	@Email(groups = Stap1.class)
	private String emailAdres;
	@NotNull(groups = Stap2.class)
	@Min(value = 1, groups = Stap2.class)
	private Integer oppervlakte;
	
	private List<String> telefoonNrs = new ArrayList<>();
	
	Map<Gazontype, Boolean> gazontypes = new LinkedHashMap<>();
	
	
	// CONSTRUCTRS
	public Offerte() {
		telefoonNrs.add("");
		for (Gazontype type : Gazontype.values()) {
			gazontypes.put(type, false);
		}
	}

	public Offerte(String voornaam, String familienaam, String emailAdres, Integer oppervlakte) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.emailAdres = emailAdres;
		this.oppervlakte = oppervlakte;
	}

	
	// GETTERS & SETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public String getEmailAdres() {
		return emailAdres;
	}
	public Integer getOppervlakte() {
		return oppervlakte;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}
	public void setOppervlakte(Integer oppervlakte) {
		this.oppervlakte = oppervlakte;
	}

	public List<String> getTelefoonNrs() {
		return telefoonNrs;
	}

	public void setTelefoonNrs(List<String> telefoonNrs) {
		this.telefoonNrs = telefoonNrs;
	}

	
	public void telefoonNrToevoegen() {
		telefoonNrs.add("");
	}

	public Map<Gazontype, Boolean> getGazontypes() {
		return gazontypes;
	}

	public void setGazontypes(Map<Gazontype, Boolean> gazontypes) {
		this.gazontypes = gazontypes;
	}
	
	
}
