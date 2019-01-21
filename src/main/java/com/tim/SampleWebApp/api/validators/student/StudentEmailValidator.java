package com.tim.SampleWebApp.api.validators.student;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.base.Validator;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;
import com.tim.SampleWebApp.error.Message;

public class StudentEmailValidator implements Validator<Object, Message> {

	public static final String fieldName = "email";
	public static final int fieldLength = 200;
	public static String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

	@Override
	public Message validate(Object toValidate) {
		Message m = new Message();
		String str = (String) toValidate;

		if (StringUtils.isBlank(str)) {
			return m.constructFromEnumForField(ApiMessages.NULL_FIELD_VALUE, fieldName);
		} else if (Pattern.compile(emailPattern).matcher(str).find()) {
			if (str.length() > fieldLength) {
				m = m.constructFromEnumForField(ApiMessages.FIELD_LENGTH_TOO_LONG, fieldName);
			}
		} else {
			m = m.constructFromEnumForField(ApiMessages.INVALID_VALUE, fieldName);
		}

		return m;
	}
}
