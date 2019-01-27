package com.tim.SampleWebApp.api.validators.course;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;

public class CourseTypeValidator implements Validator<Object, Message> {

	public static final String fieldName = "courseType";

	@Override
	public Message validate(Object toValidate) {

		String str = (String) toValidate;

		if (StringUtils.isBlank(str)) {
			return Message.isRequired(fieldName);
		} else if (!CommonConstants.CourseType.contains(str)) {
			return Message.invalidValue(fieldName);
		}

		return null;
	}
}
