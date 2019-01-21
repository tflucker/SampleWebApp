package com.tim.SampleWebApp.common;

import org.apache.commons.lang3.StringUtils;

public class CommonConstants {

	public static final String FIND_ALL_STUDENT_API_RESPONSE = "FindAllStudentApiResponse";
	public static final String FIND_STUDENT_API_RESPONSE = "FindStudentApiResponse";
	public static final String CREATE_STUDENT_API_RESPONSE = "CreateStudentApiResponse";
	public static final String UPDATE_STUDENT_API_RESPONSE = "UpdateStudentApiResponse";
	public static final String DELETE_STUDENT_API_RESPONSE = "DeleteStudentApiResponse";

	public static final String RESPONSE_MESSAGE_SUCCESS = "SUCCESS";
	public static final String RESPONSE_MESSAGE_SUCCESS_WITH_WARNING = "SUCCESS WITH WARNINGS";
	public static final String RESPONSE_MESSAGE_FAILURE = "FAILURE";

	public static final String ERROR = "ERROR";
	public static final String WARNING = "WARNING";

	public enum StudentType {
		UNDERGRAD, GRADUATE, PHD;

		public static boolean contains(String s) {
			if (!StringUtils.isBlank(s)) {
				for (StudentType type : StudentType.values()) {
					if (StringUtils.equalsIgnoreCase(type.toString(), s)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	public enum ApiMessages {

		ID_NOT_FOUND(ERROR, 1001, "ID not found."),
		NULL_FIELD_VALUE(ERROR, 1002, "No value provide for required field: "),
		INVALID_VALUE(ERROR, 1003, "Invalid value for field: "),
		NUMERIC_ONLY(ERROR, 1004, "Only numeric values allowed for field: "),
		ALPHANUMERIC_ONLY(ERROR, 1005, "Only alphanumeric values allowed for field: "),
		FIELD_LENGTH_TOO_LONG(WARNING, 1006, "Value too long for field: ");

		private String type;
		private int code;
		private String description;

		ApiMessages(String type, int code, String description) {
			this.type = type;
			this.code = code;
			this.description = description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}
}
