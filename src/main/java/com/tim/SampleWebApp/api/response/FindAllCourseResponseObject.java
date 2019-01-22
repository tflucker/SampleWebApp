package com.tim.SampleWebApp.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tim.SampleWebApp.course.Course;

@JsonInclude(Include.NON_EMPTY)
public class FindAllCourseResponseObject {

	@JsonProperty
	private String apiResponse;

	@JsonProperty
	private String responseCode;

	@JsonProperty
	private String responseMessage;

	@JsonProperty
	private List<Course> courseInfo;

	public FindAllCourseResponseObject constructFromCourse(String apiResponse, String responseCode,
			String responseMessage, List<Course> courseList) {
		FindAllCourseResponseObject response = new FindAllCourseResponseObject();
		response.setApiResponse(apiResponse);

		if (courseList != null) {
			response.setCourseInfo(courseList);
		}
		response.setResponseCode(responseCode);
		response.setResponseMessage(responseMessage);

		return response;
	}

	public FindAllCourseResponseObject() {

	}

	public FindAllCourseResponseObject(String apiResponse, String responseCode, String responseMessage,
			List<Course> courseInfo) {
		super();
		this.apiResponse = apiResponse;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.courseInfo = courseInfo;
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

	public List<Course> getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(List<Course> courseInfo) {
		this.courseInfo = courseInfo;
	}

}
