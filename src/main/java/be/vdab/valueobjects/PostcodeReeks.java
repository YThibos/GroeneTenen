package be.vdab.valueobjects;

public class PostcodeReeks {

	private static final int MIN_POSTCODE = 1000;
	private static final int MAX_POSTCODE = 9999;
	
	
	private Integer vanpostcode;
	private Integer totpostcode;
	
	public PostcodeReeks() {}

	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public void setVanpostcode(Integer vanpostcode) {
		valideerPostcode(vanpostcode);
		this.vanpostcode = vanpostcode;
	}

	public void setTotpostcode(Integer totpostcode) {
		valideerPostcode(totpostcode);
		this.totpostcode = totpostcode;
	}
	
	public boolean bevat(Integer postcode) {
		return postcode >= vanpostcode && postcode <= totpostcode;
	}
	
	private void valideerPostcode(int postcode) throws IllegalArgumentException {
		if (postcode < MIN_POSTCODE || postcode > MAX_POSTCODE) {
			throw new IllegalArgumentException();
		}
	}
	
}
