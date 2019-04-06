package com.tim.SampleWebApp.student.courses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.tim.SampleWebApp.course.Course;
import com.tim.SampleWebApp.student.Student;

@Entity
@Table(name = "STUDENT_COURSES")
@IdClass(StudentCoursesCompositeID.class)
public class StudentCourses {

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
	private Student student;

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
	private Course course;

	@Column(name = "COURSE_STATUS")
	private String courseStatus;

	@Column(name = "COMPLETION_STATUS")
	private String completionStatus;

	public StudentCourses() {
	}

	public StudentCourses(String courseStatus, String completionStatus, Student student, Course course) {
		super();
		this.courseStatus = courseStatus;
		this.completionStatus = completionStatus;
		this.student = student;
		this.course = course;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
