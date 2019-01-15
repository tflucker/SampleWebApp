package com.tim.SampleWebApp.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class Course {

	@Id
	@GeneratedValue
	private long id;

	private String courseName;

	private int minSize;

	private int maxSize;

	private String courseDescription;

	private String teacherName;

	private String courseType;

	public Course() {
		super();
	}

	public Course(long id, String courseName, int minSize, int maxSize, String courseDescription, String teacherName,
			String courseType) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.courseDescription = courseDescription;
		this.teacherName = teacherName;
		this.courseType = courseType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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