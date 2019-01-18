package com.tim.SampleWebApp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim.SampleWebApp.api.events.StudentEvents;
import com.tim.SampleWebApp.api.request.StudentApiRequest;
import com.tim.SampleWebApp.api.response.FindAllStudentResponseObject;
import com.tim.SampleWebApp.api.response.StudentResponseObject;

@RestController
public class StudentApiController extends StudentEvents {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/api/students")
	public FindAllStudentResponseObject findAll() {
		logger.info("----Returning all Students----");
		FindAllStudentResponseObject response = findAllStudents();
		return response;
	}

	@GetMapping("/api/students/{id}")
	public StudentResponseObject find(@PathVariable Long id) {
		logger.info("---- Returning student with ID: {" + id + "} ----");
		StudentResponseObject response = findById(id);
		return response;
	}

	@PostMapping("/api/students/create")
	public StudentResponseObject save(@RequestBody StudentApiRequest request) {
		logger.info("----Creating New Student----");
		logger.info("Request Body: " + request.toString());
		StudentResponseObject response = saveNewStudent(request);
		return response;
	}

	@PostMapping("/api/students/update/{id}")
	public StudentResponseObject update(@RequestBody StudentApiRequest request, @PathVariable Long id) {
		logger.info("---- Updating Student with ID: {" + id + "} ----");
		StudentResponseObject response = updateExistingStudent(request, id);
		return response;
	}

	@DeleteMapping("/api/students/delete/{id}")
	public StudentResponseObject delete(@PathVariable Long id) {
		logger.info("---- Deleting Student with ID: {" + id + "} ----");
		StudentResponseObject response = deleteStudent(id);
		return response;
	}

}
