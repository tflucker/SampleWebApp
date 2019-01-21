package com.tim.SampleWebApp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim.SampleWebApp.api.events.CourseEvents;
import com.tim.SampleWebApp.api.request.CourseApiRequest;
import com.tim.SampleWebApp.api.response.CourseResponseObject;
import com.tim.SampleWebApp.api.response.FindAllCourseResponseObject;

@RestController
public class CourseApiController extends CourseEvents {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/api/courses")
	public FindAllCourseResponseObject findAll() {
		logger.info("---- Returning all Courses ----");
		return findAllCourses();
	}

	@GetMapping(value = "/api/course/{id}")
	public CourseResponseObject findOne(@PathVariable Long id) {
		logger.info("---- Returning course with ID: {" + id + "} ----");
		return findCourse(id);
	}

	@PostMapping(value = "/api/course/create")
	public CourseResponseObject create(@RequestBody CourseApiRequest request) {
		logger.info("----Creating New Student----");
		logger.info("Request Body: " + request.toString());
		CourseResponseObject response = createNewCourse(request);
		return response;
	}

	@PostMapping(value = "/api/course/update/{id}")
	public CourseResponseObject update(@RequestBody CourseApiRequest request, @PathVariable Long id) {
		logger.info("---- Updating Student with ID: {" + id + "} ----");
		logger.info("Request Body: " + request.toString());
		CourseResponseObject response = updateCourse(request, id);
		return response;
	}

	@PostMapping(value = "/api/course/delete/{id}")
	public CourseResponseObject delete(@PathVariable Long id) {
		logger.info("---- Deleting Student with ID: {" + id + "} ----");
		CourseResponseObject response = deleteCourse(id);
		return response;
	}

}
