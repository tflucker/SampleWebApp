package com.tim.SampleWebApp.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	@Override
	public Course save(Course course) {
		course = courseRepository.save(course);
		return course;
	}

	@Override
	public void deleteById(Long id) {
		courseRepository.deleteById(id);
	}

}
