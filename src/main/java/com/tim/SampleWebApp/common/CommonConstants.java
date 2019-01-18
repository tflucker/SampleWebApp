package com.tim.SampleWebApp.common;

public class CommonConstants {

	public static final String FIND_ALL_STUDENT_API_RESPONSE = "FindAllStudentApiResponse";
	public static final String FIND_STUDENT_API_RESPONSE = "FindStudentApiResponse";
	public static final String CREATE_STUDENT_API_RESPONSE = "CreateStudentApiResponse";
	public static final String UPDATE_STUDENT_API_RESPONSE = "UpdateStudentApiResponse";
	public static final String DELETE_STUDENT_API_RESPONSE = "DeleteStudentApiResponse";

	public static final String RESPONSE_MESSAGE_SUCCESS = "SUCCESS";
	public static final String RESPONSE_MESSAGE_FAILURE = "FAILURE";

	public enum ApiMessages {

		ID_NOT_FOUND("ERROR", 1001, "ID not found.");

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
