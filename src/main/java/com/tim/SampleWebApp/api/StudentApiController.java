package com.tim.SampleWebApp.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim.SampleWebApp.api.request.StudentApiRequest;
import com.tim.SampleWebApp.api.response.StudentResponseObject;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.student.Student;
import com.tim.SampleWebApp.student.StudentRepository;

@RestController
public class StudentApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/api/students")
	public List<Student> findAll() {
		logger.info("\n----Returning all Students----\n");
		return studentRepository.findAll();
	}

	@GetMapping("/api/students/{id}")
	public StudentResponseObject findById(@PathVariable Long id) {
		logger.info("\n---- Returning student with ID: {" + id + "} ----\n");
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			return new StudentResponseObject().constructFromStudent(CommonConstants.FIND_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, student);
		}
		
		return new StudentResponseObject().constructFromStudent(CommonConstants.FIND_STUDENT_API_RESPONSE,
				HttpStatus.BAD_REQUEST.toString(), CommonConstants.RESPONSE_MESSAGE_FAILURE, null);
	}

	@PostMapping("/api/students/create")
	public StudentResponseObject saveStudent(@RequestBody StudentApiRequest request) {
		logger.info("\n----Creating New Student----\n");
		logger.info("Request Body: " + request.toString());
		Student newStudent = new Student();
		newStudent.setAddress(request.getAddress());
		newStudent.setEmail(request.getEmail());
		newStudent.setName(request.getName());
		newStudent.setPhoneNumber(request.getPhoneNumber());
		newStudent.setStudentType(request.getStudentType());

		newStudent = studentRepository.save(newStudent);

		return new StudentResponseObject().constructFromStudent(CommonConstants.CREATE_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, newStudent);
	}

	@PostMapping("/api/students/update/{id}")
	public StudentResponseObject updateStudent(@RequestBody StudentApiRequest request, @PathVariable Long id) {
		logger.info("\n---- Updating Student with ID: {" + id + "} ----\n");

		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			student.setAddress(request.getAddress());
			student.setName(request.getName());
			student.setEmail(request.getEmail());
			student.setPhoneNumber(request.getPhoneNumber());
			student.setStudentType(request.getStudentType());
			student = studentRepository.save(student);
			return new StudentResponseObject().constructFromStudent(CommonConstants.UPDATE_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, student);
		}

		return new StudentResponseObject().constructFromStudent(CommonConstants.UPDATE_STUDENT_API_RESPONSE,
				HttpStatus.BAD_REQUEST.toString(), CommonConstants.RESPONSE_MESSAGE_FAILURE, null);
	}

	@DeleteMapping("/api/students/delete/{id}")
	public StudentResponseObject deleteStudent(@PathVariable Long id) {
		logger.info("\n---- Deleting Student with ID: {" + id + "} ----\n");
		studentRepository.deleteById(id);

		return new StudentResponseObject().constructFromStudent(CommonConstants.DELETE_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, null);
	}

}
