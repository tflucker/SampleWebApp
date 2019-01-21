package com.tim.SampleWebApp.api.validators.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tim.SampleWebApp.api.request.StudentApiRequest;
import com.tim.SampleWebApp.api.validators.student.StudentAddressValidator;
import com.tim.SampleWebApp.api.validators.student.StudentEmailValidator;
import com.tim.SampleWebApp.api.validators.student.StudentNameValidator;
import com.tim.SampleWebApp.api.validators.student.StudentPhoneNumberValidator;
import com.tim.SampleWebApp.api.validators.student.StudentTypeValidator;
import com.tim.SampleWebApp.error.Message;

@Component
public class StudentValidator implements Validator<StudentApiRequest, List<Message>> {

	private Map<String, Validator<Object, Message>> validators = new HashMap<String, Validator<Object, Message>>();

	@Override
	public List<Message> validate(StudentApiRequest toValidate) {

		ValidatorImpl validatorImpl = new ValidatorImpl();

		this.validators.put("name", new StudentNameValidator());
		this.validators.put("address", new StudentAddressValidator());
		this.validators.put("phoneNumber", new StudentPhoneNumberValidator());
		this.validators.put("email", new StudentEmailValidator());
		this.validators.put("studentType", new StudentTypeValidator());

		validatorImpl.setValidators(validators);
		Validator<Object, List<Message>> validator = validatorImpl;

		return validator.validate(toValidate);
	}
}
