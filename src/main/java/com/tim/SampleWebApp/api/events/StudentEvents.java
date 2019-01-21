package com.tim.SampleWebApp.api.events;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import com.tim.SampleWebApp.api.request.StudentApiRequest;
import com.tim.SampleWebApp.api.response.FindAllStudentResponseObject;
import com.tim.SampleWebApp.api.response.StudentResponseObject;
import com.tim.SampleWebApp.api.validators.base.StudentValidator;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;
import com.tim.SampleWebApp.student.Student;
import com.tim.SampleWebApp.student.StudentService;

public class StudentEvents extends AbstractApiEvents {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentValidator validator;

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
			return generateSuccessResponse(CommonConstants.FIND_STUDENT_API_RESPONSE, student);
		}
		List<Message> messageList = new ArrayList<>();
		messageList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
		logger.info("Bad Request!");
		return generateErrorResponse(CommonConstants.FIND_STUDENT_API_RESPONSE, messageList);
	}

	public StudentResponseObject saveNewStudent(StudentApiRequest request) {
		Student newStudent = new Student();
		StudentResponseObject response = new StudentResponseObject();
		List<Message> msgList = validateRequest(request);
		if (msgList.isEmpty() || msgList == null) {
			newStudent.setAddress(request.getAddress());
			newStudent.setEmail(request.getEmail());
			newStudent.setName(request.getName());
			newStudent.setPhoneNumber(request.getPhoneNumber());
			newStudent.setStudentType(request.getStudentType());

			logger.info("Request Success!");
			response = generateSuccessResponse(CommonConstants.CREATE_STUDENT_API_RESPONSE, newStudent);
		} else if (!msgList.isEmpty() && !containsErrors(msgList)) {
			newStudent.setAddress(request.getAddress());
			newStudent.setEmail(request.getEmail());
			newStudent.setName(request.getName());
			newStudent.setPhoneNumber(request.getPhoneNumber());
			newStudent.setStudentType(request.getStudentType());

			logger.info("Request Success with Warnings!");
			response = generateSuccessWithWarningResponse(CommonConstants.CREATE_STUDENT_API_RESPONSE, newStudent,
					msgList);
		} else {
			logger.info("Bad Request!");
			return generateErrorResponse(CommonConstants.CREATE_STUDENT_API_RESPONSE, msgList);
		}

		newStudent = studentService.save(newStudent);

		return response;

	}

	public StudentResponseObject updateExistingStudent(StudentApiRequest request, Long id) {
		StudentResponseObject response = new StudentResponseObject();
		List<Message> msgList = new ArrayList<>();
		Student student = studentService.findById(id);
		if (student != null) {
			msgList = validateRequest(request);
			if (msgList.isEmpty() || msgList == null) {
				student.setAddress(request.getAddress());
				student.setName(request.getName());
				student.setEmail(request.getEmail());
				student.setPhoneNumber(request.getPhoneNumber());
				student.setStudentType(request.getStudentType());

				logger.info("Request Success!");
				response = generateSuccessResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, student);
			} else if (!msgList.isEmpty() && !containsErrors(msgList)) {
				student.setAddress(request.getAddress());
				student.setName(request.getName());
				student.setEmail(request.getEmail());
				student.setPhoneNumber(request.getPhoneNumber());
				student.setStudentType(request.getStudentType());

				logger.info("Request Success with Warnings!");
				response = generateSuccessWithWarningResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, student,
						msgList);
			} else {
				return generateErrorResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, msgList);
			}

			student = studentService.save(student);
			return response;

		} else {
			logger.info("Bad Request!");
			msgList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			return generateErrorResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, msgList);
		}
	}

	public StudentResponseObject deleteStudent(Long id) {
		List<Message> msgList = new ArrayList<>();

		try {
			studentService.deleteById(id);
		} catch (EmptyResultDataAccessException | IllegalArgumentException e) {
			msgList.add(new Message().constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			logger.info("Bad Request!");
			return generateErrorResponse(CommonConstants.DELETE_STUDENT_API_RESPONSE, msgList);
		}

		logger.info("Request Success!");
		return generateSuccessResponse(CommonConstants.DELETE_STUDENT_API_RESPONSE, null);
	}

	public List<Message> validateRequest(StudentApiRequest request) {

		List<Message> msgList = validator.validate(request);

		return msgList;
	}

	public boolean containsErrors(List<Message> msgList) {
		long errorCount = msgList.stream()
				.filter(msg -> StringUtils.equalsIgnoreCase(msg.getType(), CommonConstants.ERROR)).count();
		if (errorCount == 0L) {
			return false;
		}
		return true;
	}
}
