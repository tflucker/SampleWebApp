package com.tim.SampleWebApp.api.validators.course;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.error.Message;

public class CourseNameValidator implements Validator<Object, Message> {

	public static final String fieldName = "courseName";
	public static final int fieldLength = 200;

	@Override
	public Message validate(Object toValidate) {
		String str = (String) toValidate;

		if (StringUtils.isBlank(str)) {
			return Message.isRequired(fieldName);
		} else if (StringUtils.isAlphanumericSpace(str)) {
			if (str.length() > fieldLength) {
				return Message.fieldValueTooLong(fieldName, fieldLength);
			}
		} else {
			return Message.alphanumericOnly(fieldName);
		}

		return null;
	}
}
