package com.tim.SampleWebApp.course;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tim.SampleWebApp.student.Student;
import com.tim.SampleWebApp.student.courses.StudentCourses;

@Entity
@Table(name = "COURSE")
@SequenceGenerator(sequenceName = "course_seq", name = "course_seq", allocationSize = 1)
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "course_seq")
	private long id;

	@Column
	private String courseName;

	@Column
	private int minSize;

	@Column
	private int maxSize;

	@Column
	private String courseDescription;

	@Column
	private String teacherName;

	@Column
	private String courseType;

	@OneToMany(mappedBy="course")	
	private List<StudentCourses> studentCourseAssociations;

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

	public List<StudentCourses> getStudentCourseAssociations() {
		return studentCourseAssociations;
	}

	public void setStudentCourseAssociations(List<StudentCourses> studentCourseAssociations) {
		this.studentCourseAssociations = studentCourseAssociations;
	}

}