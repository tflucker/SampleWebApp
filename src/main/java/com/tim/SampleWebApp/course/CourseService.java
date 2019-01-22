package com.tim.SampleWebApp.course;

import java.util.List;

public interface CourseService {

	public List<Course> findAll();

	public Course findById(Long id);

	public Course save(Course course);

	public void deleteById(Long id);

}
