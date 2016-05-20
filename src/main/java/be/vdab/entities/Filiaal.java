package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import be.vdab.valueobjects.Adres;

public class Filiaal implements Serializable {

	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	private long id;
	private String naam;
	private boolean hoofdFiliaal;
	private BigDecimal waardeGebouw;
	private LocalDate inGebruikName;
	private Adres adres;

	
	// CONSTRUCTORS
	public Filiaal(String naam, boolean hoofdFiliaal, BigDecimal waardeGebouw, LocalDate inGebruikName, Adres adres) {
		this.naam = naam;
		this.hoofdFiliaal = hoofdFiliaal;
		this.waardeGebouw = waardeGebouw;
		this.inGebruikName = inGebruikName;
		this.adres = adres;
	}

	public Filiaal(long id, String naam, boolean hoofdFiliaal, BigDecimal waardeGebouw, LocalDate inGebruikName,
			Adres adres) {
		this(naam, hoofdFiliaal, waardeGebouw, inGebruikName, adres);
		this.id = id;
	}

	
	// GETTERS
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public boolean isHoofdFiliaal() {
		return hoofdFiliaal;
	}
	public BigDecimal getWaardeGebouw() {
		return waardeGebouw;
	}
	public LocalDate getInGebruikName() {
		return inGebruikName;
	}
	public Adres getAdres() {
		return adres;
	}

	// SETTERS
	public void setId(long id) {
		this.id = id;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public void setHoofdFiliaal(boolean hoofdFiliaal) {
		this.hoofdFiliaal = hoofdFiliaal;
	}
	public void setWaardeGebouw(BigDecimal waardeGebouw) {
		this.waardeGebouw = waardeGebouw;
	}
	public void setInGebruikName(LocalDate inGebruikName) {
		this.inGebruikName = inGebruikName;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	
	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Filiaal))
			return false;
		Filiaal other = (Filiaal) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filiaal [naam=" + naam + ", hoofdFiliaal=" + hoofdFiliaal + ", waardeGebouw=" + waardeGebouw
				+ ", inGebruikName=" + inGebruikName + ", adres=" + adres + "]";
	}

}
