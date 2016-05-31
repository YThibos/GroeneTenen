package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "werknemers")
public class Werknemer {
	
	private static final long serialVersionUID = 1L;

	
	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "filiaalid")
	private Filiaal filiaal;
	private BigDecimal wedde;
	private long rijksregisterNr;
	
	
	// CONSTRUCTORS
	
	
	// GETTERS
	public long getId() {
		return id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public Filiaal getFiliaal() {
		return filiaal;
	}
	public BigDecimal getWedde() {
		return wedde;
	}
	public long getRijksregisterNr() {
		return rijksregisterNr;
	}

	
	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rijksregisterNr ^ (rijksregisterNr >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Werknemer))
			return false;
		Werknemer other = (Werknemer) obj;
		if (rijksregisterNr != other.rijksregisterNr)
			return false;
		return true;
	}
	
}
