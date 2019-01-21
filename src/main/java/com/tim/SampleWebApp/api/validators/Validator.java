package com.tim.SampleWebApp.api.validators.base;

public interface Validator<I, O> {

	
	public O validate(I toValidate);
}
