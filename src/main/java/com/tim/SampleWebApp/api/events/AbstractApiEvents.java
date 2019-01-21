package com.tim.SampleWebApp.api.events;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.tim.SampleWebApp.api.response.StudentResponseObject;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;
import com.tim.SampleWebApp.student.Student;

public abstract class AbstractApiEvents {

	public static StudentResponseObject generateSuccessResponse(String apiResponse, Student student) {
		StudentResponseObject response = new StudentResponseObject();

		response.setApiResponse(apiResponse);
		response.setStudentInfo(student);
		response.setResponseCode(HttpStatus.OK.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_SUCCESS);

		return response;
	}

	public static StudentResponseObject generateSuccessWithWarningResponse(String apiResponse, Student student,
			List<Message> messageList) {
		StudentResponseObject response = new StudentResponseObject();

		response.setApiResponse(apiResponse);
		response.setStudentInfo(student);
		response.setResponseCode(HttpStatus.OK.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_SUCCESS_WITH_WARNING);
		response.setMessageList(messageList);

		return response;
	}

	public static StudentResponseObject generateErrorResponse(String apiResponse, List<Message> messageList) {
		StudentResponseObject response = new StudentResponseObject();

		response.setApiResponse(apiResponse);
		response.setResponseCode(HttpStatus.BAD_REQUEST.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_FAILURE);
		response.setMessageList(messageList);

		return response;
	}

}
