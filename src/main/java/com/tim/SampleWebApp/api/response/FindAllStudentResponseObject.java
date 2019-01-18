package com.tim.SampleWebApp.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tim.SampleWebApp.student.Student;

@JsonInclude(Include.NON_EMPTY)
public class FindAllStudentResponseObject {

	@JsonProperty
	private String apiResponse;

	@JsonProperty
	private String responseCode;

	@JsonProperty
	private String responseMessage;

	@JsonProperty
	private List<Student> studentInfo;

	public FindAllStudentResponseObject constructFromStudent(String apiResponse, String responseCode,
			String responseMessage, List<Student> studentList) {
		FindAllStudentResponseObject response = new FindAllStudentResponseObject();
		response.setApiResponse(apiResponse);

		if (studentList != null) {
			response.setStudentInfo(studentList);
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

	public List<Student> getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(List<Student> studentInfo) {
		this.studentInfo = studentInfo;
	}

}
