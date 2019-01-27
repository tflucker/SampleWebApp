package com.tim.SampleWebApp.api.validators.student;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;
import com.tim.SampleWebApp.error.Message;

public class StudentPhoneNumberValidator implements Validator<Object, Message> {

	public static final String fieldName = "phoneNumber";
	public static final String phoneNumberPattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

	@Override
	public Message validate(Object toValidate) {
		String str = (String) toValidate;
		if (StringUtils.isBlank(str)) {
			return Message.isRequired(fieldName);
		} else if (!Pattern.compile(phoneNumberPattern).matcher(str).find()) {
			return Message.invalidValue(fieldName);
		}

		return null;

	}
}
