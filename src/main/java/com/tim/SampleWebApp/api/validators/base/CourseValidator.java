package com.tim.SampleWebApp.api.validators.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tim.SampleWebApp.api.request.CourseApiRequest;
import com.tim.SampleWebApp.api.validators.Validator;
import com.tim.SampleWebApp.api.validators.ValidatorImpl;
import com.tim.SampleWebApp.api.validators.course.CourseDescriptionValidator;
import com.tim.SampleWebApp.api.validators.course.CourseMaxSizeValidator;
import com.tim.SampleWebApp.api.validators.course.CourseMinSizeValidator;
import com.tim.SampleWebApp.api.validators.course.CourseNameValidator;
import com.tim.SampleWebApp.api.validators.course.CourseTeacherNameValidator;
import com.tim.SampleWebApp.api.validators.course.CourseTypeValidator;
import com.tim.SampleWebApp.error.Message;

@Component
public class CourseValidator implements Validator<CourseApiRequest, List<Message>> {

	private Map<String, Validator<Object, Message>> validators = new HashMap<String, Validator<Object, Message>>();

	@Override
	public List<Message> validate(CourseApiRequest toValidate) {
		ValidatorImpl validatorImpl = new ValidatorImpl();

		this.validators.put("courseName", new CourseNameValidator());
		this.validators.put("minSize", new CourseMinSizeValidator());
		this.validators.put("maxSize", new CourseMaxSizeValidator());
		this.validators.put("teacherName", new CourseTeacherNameValidator());
		this.validators.put("courseDescription", new CourseDescriptionValidator());
		this.validators.put("courseType", new CourseTypeValidator());

		validatorImpl.setValidators(validators);
		Validator<Object, List<Message>> validator = validatorImpl;

		return validator.validate(toValidate);

	}

}
