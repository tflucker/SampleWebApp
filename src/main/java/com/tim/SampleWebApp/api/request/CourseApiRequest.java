package com.tim.SampleWebApp.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class CourseApiRequest {

	@JsonProperty
	private String courseName;

	@JsonProperty
	private int minSize;

	@JsonProperty
	private int maxSize;

	@JsonProperty
	private String courseDescription;

	@JsonProperty
	private String teacherName;

	@JsonProperty
	private String courseType;

	public CourseApiRequest() {
	}

	public CourseApiRequest(String courseName, int minSize, int maxSize, String courseDescription, String teacherName,
			String courseType) {
		super();
		this.courseName = courseName;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.courseDescription = courseDescription;
		this.teacherName = teacherName;
		this.courseType = courseType;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

}
