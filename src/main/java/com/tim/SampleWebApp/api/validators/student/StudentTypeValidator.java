package com.tim.SampleWebApp.api.validators.student;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;
import com.tim.SampleWebApp.error.Message;

public class StudentTypeValidator implements Validator<Object, Message> {

	public static final String fieldName = "studentType";

	@Override
	public Message validate(Object obj) {
		String toValidate = (String) obj;

		if (StringUtils.isBlank(toValidate)) {
			return Message.isRequired(fieldName);
		} else if (!CommonConstants.StudentType.contains(toValidate)) {
			return Message.invalidValue(fieldName);
		}
		return null;
	}

}
