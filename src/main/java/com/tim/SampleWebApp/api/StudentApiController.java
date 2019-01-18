package com.tim.SampleWebApp.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim.SampleWebApp.api.request.StudentApiRequest;
import com.tim.SampleWebApp.api.response.FindAllStudentResponseObject;
import com.tim.SampleWebApp.api.response.StudentResponseObject;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;
import com.tim.SampleWebApp.student.Student;
import com.tim.SampleWebApp.student.StudentService;

@RestController
public class StudentApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentService studentService;

	@GetMapping("/api/students")
	public FindAllStudentResponseObject findAll() {
		logger.info("----Returning all Students----");
		return new FindAllStudentResponseObject().constructFromStudent(CommonConstants.FIND_ALL_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, studentService.findAll());
	}

	@GetMapping("/api/students/{id}")
	public StudentResponseObject findById(@PathVariable Long id) {
		logger.info("---- Returning student with ID: {" + id + "} ----");
		Student student = studentService.findById(id);
		if (student != null) {
			return new StudentResponseObject().constructFromStudent(CommonConstants.FIND_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, student, null);
		}
		List<Message> messageList = new ArrayList<>();
		messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
		return new StudentResponseObject().constructFromStudent(CommonConstants.FIND_STUDENT_API_RESPONSE,
				HttpStatus.BAD_REQUEST.toString(), CommonConstants.RESPONSE_MESSAGE_FAILURE, null, messageList);
	}

	@PostMapping("/api/students/create")
	public StudentResponseObject saveStudent(@RequestBody StudentApiRequest request) {
		logger.info("----Creating New Student----");
		logger.info("Request Body: " + request.toString());
		Student newStudent = new Student();
		newStudent.setAddress(request.getAddress());
		newStudent.setEmail(request.getEmail());
		newStudent.setName(request.getName());
		newStudent.setPhoneNumber(request.getPhoneNumber());
		newStudent.setStudentType(request.getStudentType());

		newStudent = studentService.save(newStudent);

		return new StudentResponseObject().constructFromStudent(CommonConstants.CREATE_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, newStudent, null);
	}

	@PostMapping("/api/students/update/{id}")
	public StudentResponseObject updateStudent(@RequestBody StudentApiRequest request, @PathVariable Long id) {
		logger.info("---- Updating Student with ID: {" + id + "} ----");

		Student student = studentService.findById(id);
		if (student != null) {
			student.setAddress(request.getAddress());
			student.setName(request.getName());
			student.setEmail(request.getEmail());
			student.setPhoneNumber(request.getPhoneNumber());
			student.setStudentType(request.getStudentType());
			student = studentService.save(student);
			return new StudentResponseObject().constructFromStudent(CommonConstants.UPDATE_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, student, null);
		}
		List<Message> messageList = new ArrayList<>();
		messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
		return new StudentResponseObject().constructFromStudent(CommonConstants.UPDATE_STUDENT_API_RESPONSE,
				HttpStatus.BAD_REQUEST.toString(), CommonConstants.RESPONSE_MESSAGE_FAILURE, null, messageList);
	}

	@DeleteMapping("/api/students/delete/{id}")
	public StudentResponseObject deleteStudent(@PathVariable Long id) {
		logger.info("---- Deleting Student with ID: {" + id + "} ----");
		try {
			studentService.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			List<Message> messageList = new ArrayList<>();
			messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			return new StudentResponseObject().constructFromStudent(CommonConstants.DELETE_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, null, messageList);
		}
		return new StudentResponseObject().constructFromStudent(CommonConstants.DELETE_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, null, null);
	}

}
