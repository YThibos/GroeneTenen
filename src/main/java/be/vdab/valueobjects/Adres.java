package be.vdab.valueobjects;

import java.io.Serializable;

public class Adres implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// MEMBER VARIABLES
	private String straat;
	private String huisNr;
	private Integer postcode;
	private String gemeente;

	
	// CONSTRUCTOR
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