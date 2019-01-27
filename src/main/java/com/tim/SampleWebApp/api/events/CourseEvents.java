package com.tim.SampleWebApp.api.events;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import com.tim.SampleWebApp.api.request.CourseApiRequest;
import com.tim.SampleWebApp.api.response.CourseResponseObject;
import com.tim.SampleWebApp.api.response.FindAllCourseResponseObject;
import com.tim.SampleWebApp.api.validators.base.CourseValidator;
import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;
import com.tim.SampleWebApp.course.Course;
import com.tim.SampleWebApp.course.CourseService;
import com.tim.SampleWebApp.error.Message;

public class CourseEvents extends AbstractApiEvents {

	private static Logger logger = LoggerFactory.getLogger(CourseEvents.class);

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseValidator validator;

	public FindAllCourseResponseObject findAllCourses() {
		List<Course> courseList = courseService.findAll();
		logger.info("Request Success!");
		return new FindAllCourseResponseObject(CommonConstants.FIND_ALL_COURSE_API_RESPONSE, HttpStatus.OK.toString(),
				CommonConstants.RESPONSE_MESSAGE_SUCCESS, courseList);

	}

	public CourseResponseObject findCourse(Long id) {
		CourseResponseObject response = new CourseResponseObject();
		Course course = courseService.findById(id);
		if (course != null) {
			logger.info("Request Success!");
			response = generateSuccessResponse(CommonConstants.FIND_COURSE_API_RESPONSE, course);
		} else {
			logger.info("Bad Request!");
			List<Message> msgList = new ArrayList<>();
			msgList.add(Message.constructFromEnum(ApiMessages.ID_NOT_FOUND));
			response = generateErrorResponse(CommonConstants.FIND_COURSE_API_RESPONSE, msgList);
		}
		return response;
	}

	public CourseResponseObject createNewCourse(CourseApiRequest request) {
		CourseResponseObject response = new CourseResponseObject();
		Course course = new Course();

		List<Message> msgList = validate(request);
		if (msgList.isEmpty() || msgList == null) {
			course = setCourse(request);
			response = generateSuccessResponse(CommonConstants.CREATE_COURSE_API_RESPONSE, course);
		} else if (!msgList.isEmpty() && !containsErrors(msgList)) {
			course = setCourse(request);
			response = generateSuccessWithWarningResponse(CommonConstants.CREATE_COURSE_API_RESPONSE, course, msgList);
		} else {
			return generateErrorResponse(CommonConstants.CREATE_COURSE_API_RESPONSE, msgList);
		}

		course = courseService.save(course);

		return response;
	}

	public CourseResponseObject updateCourse(CourseApiRequest request, Long id) {
		CourseResponseObject response = new CourseResponseObject();
		List<Message> msgList = new ArrayList<>();
		Course course = courseService.findById(id);
		if (course != null) {
			msgList = validate(request);
			if (msgList.isEmpty() || msgList == null) {
				course = setCourse(request);
				response = generateSuccessResponse(CommonConstants.UPDATE_COURSE_API_RESPONSE, course);
			} else if (!msgList.isEmpty() && !containsErrors(msgList)) {
				course = setCourse(request);
				response = generateSuccessWithWarningResponse(CommonConstants.UPDATE_COURSE_API_RESPONSE, course,
						msgList);
			} else {
				return generateErrorResponse(CommonConstants.UPDATE_COURSE_API_RESPONSE, msgList);
			}
			courseService.save(course);
			return response;
		} else {
			msgList.add(Message.constructFromEnum(ApiMessages.ID_NOT_FOUND));
			return generateErrorResponse(CommonConstants.UPDATE_COURSE_API_RESPONSE, msgList);
		}
	}

	public CourseResponseObject deleteCourse(Long id) {
		CourseResponseObject response = new CourseResponseObject();
		try {
			courseService.deleteById(id);
			response = generateSuccessResponse(CommonConstants.DELETE_COURSE_API_RESPONSE, null);
		} catch (IllegalArgumentException | EmptyResultDataAccessException e) {
			List<Message> msgList = new ArrayList<>();
			msgList.add(Message.constructFromEnum(ApiMessages.ID_NOT_FOUND));
			response = generateErrorResponse(CommonConstants.DELETE_COURSE_API_RESPONSE, msgList);
		}

		return response;
	}

	public List<Message> validate(CourseApiRequest request) {

		List<Message> msgList = validator.validate(request);

		return msgList;
	}

	public static CourseResponseObject generateSuccessResponse(String apiName, Course course) {
		logger.info("Request Succeeded, generating response...");

		CourseResponseObject response = new CourseResponseObject();

		response.setApiResponse(apiName);
		response.setResponseCode(HttpStatus.OK.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_SUCCESS);
		response.setCourseInfo(course);

		return response;
	}

	public static CourseResponseObject generateSuccessWithWarningResponse(String apiName, Course course,
			List<Message> messageList) {
		logger.info("Request Succeeded with warnings, generating response...");

		CourseResponseObject response = new CourseResponseObject();

		response.setApiResponse(apiName);
		response.setResponseCode(HttpStatus.OK.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_SUCCESS_WITH_WARNING);

		response.setCourseInfo(course);
		response.setMessageList(messageList);
		return response;
	}

	public static CourseResponseObject generateErrorResponse(String apiName, List<Message> messageList) {
		logger.info("Request failed, generating error response...");

		CourseResponseObject response = new CourseResponseObject();

		response.setApiResponse(apiName);
		response.setResponseCode(HttpStatus.BAD_REQUEST.toString());
		response.setResponseMessage(CommonConstants.RESPONSE_MESSAGE_FAILURE);

		response.setMessageList(messageList);

		return response;
	}

	public static Course setCourse(CourseApiRequest request) {
		Course course = new Course();
		course.setCourseName(request.getCourseName());
		course.setCourseDescription(request.getCourseDescription());
		course.setCourseType(CommonConstants.CourseType.valueOf(request.getCourseType()).toString());
		course.setMinSize(request.getMinSize());
		course.setMaxSize(request.getMaxSize());
		course.setTeacherName(request.getTeacherName());

		return course;
	}
}
