package com.tim.SampleWebApp.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tim.SampleWebApp.common.CommonConstants.ApiMessages;

@JsonInclude(Include.NON_EMPTY)
public class Message {

	@JsonProperty
	private String type;

	@JsonProperty
	private int code;

	@JsonProperty
	private String description;

	public Message() {

	}

	public static Message constructFromEnum(ApiMessages apiMessages) {
		Message msg = new Message();
		msg.setCode(apiMessages.getCode());
		msg.setType(apiMessages.getType());
		msg.setDescription(apiMessages.getDescription());

		return msg;
	}

	public static Message constructFromEnumForField(ApiMessages apiMessage, String fieldName) {
		Message msg = new Message();
		msg.setCode(apiMessage.getCode());
		msg.setType(apiMessage.getType());
		msg.setDescription(apiMessage.getDescription() + fieldName);
		return msg;
	}

	public static Message alphanumericOnly(String fieldName) {
		return constructFromEnumForField(ApiMessages.ALPHANUMERIC_ONLY, fieldName);
	}

	public static Message numericOnly(String fieldName) {
		return constructFromEnumForField(ApiMessages.NUMERIC_ONLY, fieldName);
	}

	public static Message isRequired(String fieldName) {
		return constructFromEnumForField(ApiMessages.NULL_FIELD_VALUE, fieldName);
	}

	public static Message fieldValueTooLong(String fieldName, int fieldLength) {
		Message msg = constructFromEnumForField(ApiMessages.FIELD_LENGTH_TOO_LONG, fieldName);
		msg.setDescription(
				ApiMessages.FIELD_LENGTH_TOO_LONG.getDescription() + fieldName + ", truncated to database length");
		return msg;
	}

	public static Message invalidValue(String fieldName) {
		return constructFromEnumForField(ApiMessages.INVALID_VALUE, fieldName);
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
