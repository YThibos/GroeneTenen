package be.vdab.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Min;

public class PostcodeValidator implements ConstraintValidator<PostcodeOld, Integer>{

	private static final int MIN_POSTCODE = 1000;
	private static final int MAX_POSTCODE = 9999;
	
	@Override
	public void initialize(PostcodeOld constraintAnnotation) {}

	@Override
	public boolean isValid(Integer postcode, ConstraintValidatorContext context) {
		return postcode == null || (postcode >= MIN_POSTCODE && postcode <= MAX_POSTCODE);
	}
	
}
