package be.vdab.valueobjects;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import be.vdab.constraints.Postcode;

public class Adres implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// MEMBER VARIABLES
	@NotBlank
	@Length(min = 1, max = 50)
	private String straat;
	@NotBlank
	@Length(min = 1, max = 7)
	private String huisNr;
	@NotNull
	@Postcode
	private Integer postcode;
	@NotBlank
	@Length(min = 1, max = 50)
	private String gemeente;

	
	// CONSTRUCTORS
	public Adres() {}
	
	public Adres(String straat, String huisNr, Integer postcode, String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}

	
	// GETTERS
	public final String getStraat() {
		return straat;
	}
	public final String getHuisNr() {
		return huisNr;
	}
	public final Integer getPostcode() {
		return postcode;
	}
	public final String getGemeente() {
		return gemeente;
	}
	
	
	
}