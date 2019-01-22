package com.tim.SampleWebApp.api.validators;

public interface Validator<I, O> {

	
	public O validate(I toValidate);
}
