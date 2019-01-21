package com.tim.SampleWebApp.api.validators.student;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.base.Validator;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;
import com.tim.SampleWebApp.error.Message;

public class StudentPhoneNumberValidator implements Validator<Object, Message> {

	public static final String fieldName = "phoneNumber";
	public static final String phoneNumberPattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

	@Override
	public Message validate(Object toValidate) {
		Message m = new Message();
		String str = (String) toValidate;
		if (StringUtils.isBlank(str)) {
			return m.constructFromEnumForField(ApiMessages.NULL_FIELD_VALUE, fieldName);
		} else if (!Pattern.compile(phoneNumberPattern).matcher(str).find()) {
			m = m.constructFromEnumForField(ApiMessages.INVALID_VALUE, fieldName);
		}

		return m;

	}
}
