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
import com.tim.SampleWebApp.api.validators.base.StudentValidator;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;
import com.tim.SampleWebApp.student.Student;
import com.tim.SampleWebApp.student.StudentService;

public class StudentEvents extends AbstractApiEvents {

	private static Logger logger = LoggerFactory.getLogger(StudentEvents.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentValidator validator;

	public FindAllStudentResponseObject findAllStudents() {
		List<Student> studentList = studentService.findAll();
		logger.info("Request Success!");
		return FindAllStudentResponseObject.constructFromStudent(CommonConstants.FIND_ALL_STUDENT_API_RESPONSE,
				HttpStatus.OK.toString(), CommonConstants.RESPONSE_MESSAGE_SUCCESS, studentList);
	}

	public StudentResponseObject findById(Long id) {
		StudentResponseObject response = new StudentResponseObject();
		Student student = studentService.findById(id);
		if (student != null) {
			response = generateSuccessResponse(CommonConstants.FIND_STUDENT_API_RESPONSE, student);
		} else {
			List<Message> messageList = new ArrayList<>();
			messageList.add(Message.constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			response = generateErrorResponse(CommonConstants.FIND_STUDENT_API_RESPONSE, messageList);
		}
		return response;
	}

	public StudentResponseObject saveNewStudent(StudentApiRequest request) {
		Student student = new Student();
		StudentResponseObject response = new StudentResponseObject();
		List<Message> msgList = validateRequest(request);
		if (msgList.isEmpty() || msgList == null) {
			student = setStudent(request);
			response = generateSuccessResponse(CommonConstants.CREATE_STUDENT_API_RESPONSE, student);
		} else if (!msgList.isEmpty() && !containsErrors(msgList)) {
			student = setStudent(request);
			response = generateSuccessWithWarningResponse(CommonConstants.CREATE_STUDENT_API_RESPONSE, student,
					msgList);
		} else {
			return generateErrorResponse(CommonConstants.CREATE_STUDENT_API_RESPONSE, msgList);
		}

		student = studentService.save(student);

		return response;

	}

	public StudentResponseObject updateExistingStudent(StudentApiRequest request, Long id) {
		StudentResponseObject response = new StudentResponseObject();
		List<Message> msgList = new ArrayList<>();
		Student student = studentService.findById(id);
		if (student != null) {
			msgList = validateRequest(request);
			if (msgList.isEmpty() || msgList == null) {
				student = setStudent(request);
				response = generateSuccessResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, student);
			} else if (!msgList.isEmpty() && !containsErrors(msgList)) {
				student = setStudent(request);
				response = generateSuccessWithWarningResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, student,
						msgList);
			} else {
				return generateErrorResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, msgList);
			}

			student = studentService.save(student);
			return response;

		} else {
			msgList.add(Message.constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			return generateErrorResponse(CommonConstants.UPDATE_STUDENT_API_RESPONSE, msgList);
		}
	}

	public StudentResponseObject deleteStudent(Long id) {
		StudentResponseObject response = new StudentResponseObject();
		List<Message> msgList = new ArrayList<>();

		try {
			studentService.deleteById(id);
			response = generateSuccessResponse(CommonConstants.DELETE_STUDENT_API_RESPONSE, null);
		} catch (EmptyResultDataAccessException | IllegalArgumentException e) {
			msgList.add(Message.constructFromEnum(CommonConstants.ApiMessages.ID_NOT_FOUND));
			response = generateErrorResponse(CommonConstants.DELETE_STUDENT_API_RESPONSE, msgList);
		}

		return response;
	}

	public List<Message> validateRequest(StudentApiRequest request) {
		List<Message> msgList = validator.validate(request);
		return msgList;
	}

	public static StudentResponseObject generateSuccessResponse(String apiName, Student student) {
		logger.info("Request Succeeded, generating response...");

		StudentResponseObject response = new StudentResponseObject();

		response.setApiResponse(apiName);
		response.setResponseCode(HttpStatus.OK.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_SUCCESS);
		response.setStudentInfo(student);

		return response;
	}

	public static StudentResponseObject generateSuccessWithWarningResponse(String apiName, Student student,
			List<Message> messageList) {
		logger.info("Request Succeeded with warnings, generating response...");
		StudentResponseObject response = new StudentResponseObject();

		response.setApiResponse(apiName);
		response.setResponseCode(HttpStatus.OK.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_SUCCESS_WITH_WARNING);

		response.setStudentInfo(student);
		response.setMessageList(messageList);
		return response;
	}

	public static StudentResponseObject generateErrorResponse(String apiName, List<Message> messageList) {
		logger.info("Request failed, generating error response...");

		StudentResponseObject response = new StudentResponseObject();

		response.setApiResponse(apiName);
		response.setResponseCode(HttpStatus.BAD_REQUEST.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_FAILURE);

		response.setMessageList(messageList);

		return response;
	}
	
	public static Student setStudent(StudentApiRequest request) {
		Student student = new Student();
		student.setAddress(request.getAddress());
		student.setEmail(request.getEmail());
		student.setName(request.getName());
		student.setPhoneNumber(request.getPhoneNumber());
		student.setStudentType(request.getStudentType());
		return student;
	}

}
