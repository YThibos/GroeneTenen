package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "filialen")
@XmlRootElement
public class Filiaal implements Serializable {

	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long versie;
	
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String naam;
	
	private boolean hoofdFiliaal;
	
	@NumberFormat(style=Style.NUMBER) 
	@Min(0)
	@NotNull
	@Digits(integer = 10, fraction = 2)
	private BigDecimal waardeGebouw;
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column
	@Convert(converter = SqlDateLocalDateConverter.class)
	private LocalDate inGebruikName;
	
	@Valid
	@Embedded
	private Adres adres;
	
	@OneToMany(mappedBy = "filiaal")
	@XmlTransient @JsonIgnore
	private Set<Werknemer> werknemers;
	
	
	// CONSTRUCTORS
	public Filiaal() {}
	
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
	public Set<Werknemer> getWerknemers() {
		return werknemers;
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
	
	
	// OPERATIONS
	public void afschrijven() {
		this.waardeGebouw = BigDecimal.ZERO;
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
