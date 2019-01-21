package com.tim.SampleWebApp.api.validators.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tim.SampleWebApp.api.request.CourseApiRequest;
import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.api.validators.ValidatorImpl;
import com.tim.SampleWebApp.api.validators.student.StudentAddressValidator;
import com.tim.SampleWebApp.api.validators.student.StudentEmailValidator;
import com.tim.SampleWebApp.api.validators.student.StudentNameValidator;
import com.tim.SampleWebApp.api.validators.student.StudentPhoneNumberValidator;
import com.tim.SampleWebApp.api.validators.student.StudentTypeValidator;
import com.tim.SampleWebApp.error.Message;

@Component
public class CourseValidator implements Validator<CourseApiRequest, List<Message>> {

	private Map<String, Validator<Object, Message>> validators = new HashMap<String, Validator<Object, Message>>();

	@Override
	public List<Message> validate(CourseApiRequest toValidate) {
		ValidatorImpl validatorImpl = new ValidatorImpl();

		this.validators.put("courseName", new StudentNameValidator());
		this.validators.put("minSize", new StudentAddressValidator());
		this.validators.put("maxSize", new StudentPhoneNumberValidator());
		this.validators.put("courseDescription", new StudentEmailValidator());
		this.validators.put("courseType", new StudentTypeValidator());

		validatorImpl.setValidators(validators);
		Validator<Object, List<Message>> validator = validatorImpl;

		return validator.validate(toValidate);

	}

}
