package com.tim.SampleWebApp.api.events;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import com.tim.SampleWebApp.api.request.StudentApiRequest;
import com.tim.SampleWebApp.api.response.FindAllStudentResponseObject;
import com.tim.SampleWebApp.api.response.StudentResponseObject;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;
import com.tim.SampleWebApp.student.Student;
import com.tim.SampleWebApp.student.StudentService;

public class StudentEvents {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentService studentService;

	public FindAllStudentResponseObject findAllStudents() {
		List<Student> studentList = studentService.findAll();
		logger.info("Request Success!");
		return new FindAllStudentResponseObject().constructFromStudent(CommonConstants.FIND_ALL_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, studentList);
	}

	public StudentResponseObject findById(Long id) {
		Student student = studentService.findById(id);
		if (student != null) {
			logger.info("Request Success!");
			return new StudentResponseObject().constructFromStudent(CommonConstants.FIND_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, student, null);
		}
		List<Message> messageList = new ArrayList<>();
		messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
		logger.info("Bad Request!");
		return new StudentResponseObject().constructFromStudent(CommonConstants.FIND_STUDENT_API_RESPONSE,
				HttpStatus.BAD_REQUEST.toString(), CommonConstants.RESPONSE_MESSAGE_FAILURE, null, messageList);
	}

	public StudentResponseObject saveNewStudent(StudentApiRequest request) {

		Student newStudent = new Student();
		newStudent.setAddress(request.getAddress());
		newStudent.setEmail(request.getEmail());
		newStudent.setName(request.getName());
		newStudent.setPhoneNumber(request.getPhoneNumber());
		newStudent.setStudentType(request.getStudentType());

		newStudent = studentService.save(newStudent);

		logger.info("Request Success!");
		return new StudentResponseObject().constructFromStudent(CommonConstants.CREATE_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, newStudent, null);

	}

	public StudentResponseObject updateExistingStudent(StudentApiRequest request, Long id) {

		Student student = studentService.findById(id);
		if (student != null) {
			student.setAddress(request.getAddress());
			student.setName(request.getName());
			student.setEmail(request.getEmail());
			student.setPhoneNumber(request.getPhoneNumber());
			student.setStudentType(request.getStudentType());
			student = studentService.save(student);

			logger.info("Request Success!");
			return new StudentResponseObject().constructFromStudent(CommonConstants.UPDATE_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, student, null);
		}
		List<Message> messageList = new ArrayList<>();
		messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
		logger.info("Bad Request!");
		return new StudentResponseObject().constructFromStudent(CommonConstants.UPDATE_STUDENT_API_RESPONSE,
				HttpStatus.BAD_REQUEST.toString(), CommonConstants.RESPONSE_MESSAGE_FAILURE, null, messageList);

	}

	public StudentResponseObject deleteStudent(Long id) {

		try {
			studentService.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			List<Message> messageList = new ArrayList<>();
			messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			logger.info("Bad Request!");
			return new StudentResponseObject().constructFromStudent(CommonConstants.DELETE_STUDENT_API_RESPONSE,
					HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, null, messageList);
		}

		logger.info("Request Success!");
		return new StudentResponseObject().constructFromStudent(CommonConstants.DELETE_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, null, null);
	}
}
