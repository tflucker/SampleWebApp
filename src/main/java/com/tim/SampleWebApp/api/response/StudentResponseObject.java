package com.tim.SampleWebApp.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tim.SampleWebApp.student.Student;

@JsonInclude(Include.NON_EMPTY)
public class StudentResponseObject {

	@JsonProperty
	private String apiResponse;

	@JsonProperty
	private String responseCode;

	@JsonProperty
	private String responseMessage;

	@JsonProperty
	private Student studentInfo;

	public StudentResponseObject() {

	}

	public StudentResponseObject constructFromStudent(String apiResponse, String responseCode, String responseMessage,
			Student student) {
		StudentResponseObject response = new StudentResponseObject();
		response.setApiResponse(apiResponse);

		if (student != null) {
			response.setStudentInfo(student);
		}
		response.setResponseCode(responseCode);
		response.setResponseMessage(responseMessage);

		return response;
	}

	public String getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Student getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(Student studentInfo) {
		this.studentInfo = studentInfo;
	}

}