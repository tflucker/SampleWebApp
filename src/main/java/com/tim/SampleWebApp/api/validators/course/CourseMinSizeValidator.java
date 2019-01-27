package com.tim.SampleWebApp.api.validators.course;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.error.Message;

public class CourseMinSizeValidator implements Validator<Object, Message> {

	public static final String fieldName = "minSize";

	@Override
	public Message validate(Object toValidate) {
		int val = (Integer) toValidate;
		if (val == 0) {
			return Message.isRequired(fieldName);
		}

		return null;
	}
}
