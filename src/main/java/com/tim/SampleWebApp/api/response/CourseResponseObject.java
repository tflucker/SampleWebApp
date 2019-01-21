package com.tim.SampleWebApp.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tim.SampleWebApp.course.Course;
import com.tim.SampleWebApp.error.Message;

@JsonInclude(Include.NON_EMPTY)
public class CourseResponseObject {

	@JsonProperty
	private String apiResponse;

	@JsonProperty
	private String responseCode;

	@JsonProperty
	private String responseMessage;

	@JsonProperty
	private Course courseInfo;

	@JsonProperty
	private List<Message> messageList;

	public CourseResponseObject() {

	}

	public CourseResponseObject(String apiResponse, String responseCode, String responseMessage, Course courseInfo,
			List<Message> messageList) {
		super();
		this.apiResponse = apiResponse;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.courseInfo = courseInfo;
		this.messageList = messageList;
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

	public Course getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(Course courseInfo) {
		this.courseInfo = courseInfo;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

}
