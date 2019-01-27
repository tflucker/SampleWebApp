package com.tim.SampleWebApp.api.validators.course;

import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.error.Message;

public class CourseMaxSizeValidator implements Validator<Object, Message> {

	public static final String fieldName = "maxSize";

	@Override
	public Message validate(Object toValidate) {
		int val = (Integer) toValidate;
		if (val == 0) {
			return Message.isRequired(fieldName);
		}

		return null;
	}
}
