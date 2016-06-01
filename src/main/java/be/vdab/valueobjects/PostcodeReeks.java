package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanTot;

@PostcodeReeksVanKleinerDanTot
public class PostcodeReeks {

	@NotNull @Postcode
	private Integer vanpostcode;
	@NotNull @Postcode
	private Integer totpostcode;
	
	public PostcodeReeks() {}

	public Integer getVanpostcode() {
		return vanpostcode;
	}
	public Integer getTotpostcode() {
		return totpostcode;
	}

	public void setVanpostcode(Integer vanpostcode) {
		// obsolete: valideerPostcode(vanpostcode);
		this.vanpostcode = vanpostcode;
	}

	public void setTotpostcode(Integer totpostcode) {
		// obsolete: valideerPostcode(totpostcode);
		this.totpostcode = totpostcode;
	}
	
	public boolean bevat(Integer postcode) {
		return postcode >= vanpostcode && postcode <= totpostcode;
	}
	
	// overbodig door validation annotations
//	private void valideerPostcode(int postcode) throws IllegalArgumentException {
//		if (postcode < MIN_POSTCODE || postcode > MAX_POSTCODE) {
//			throw new IllegalArgumentException();
//		}
//	}
	
}
