package com.tim.SampleWebApp.api.validators.student;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.base.Validator;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;
import com.tim.SampleWebApp.error.Message;

public class StudentNameValidator implements Validator<Object, Message> {

	public static final String fieldName = "name";
	public static final int fieldLength = 200;

	@Override
	public Message validate(Object toValidate) {
		Message m = new Message();
		String str = (String) toValidate;
		
		if(StringUtils.isBlank(str)) {
			return m.constructFromEnumForField(ApiMessages.NULL_FIELD_VALUE, fieldName);
		}else if (StringUtils.isAlphanumericSpace(str)) {
			if(str.length() > fieldLength) {
				m = m.constructFromEnumForField(ApiMessages.FIELD_LENGTH_TOO_LONG, fieldName);
			}
		}else {
			m = m.constructFromEnumForField(ApiMessages.ALPHANUMERIC_ONLY, fieldName);
		}
		
		return m;
	}

}
