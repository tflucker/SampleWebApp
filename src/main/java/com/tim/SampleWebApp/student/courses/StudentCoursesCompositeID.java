package com.tim.SampleWebApp.student.courses;

import java.io.Serializable;

public class StudentCoursesCompositeID implements Serializable {

	private static final long serialVersionUID = 1L;

	private long student;

	private long course;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (course ^ (course >>> 32));
		result = prime * result + (int) (student ^ (student >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCoursesCompositeID other = (StudentCoursesCompositeID) obj;
		if (course != other.course)
			return false;
		if (student != other.student)
			return false;
		return true;
	}

}
