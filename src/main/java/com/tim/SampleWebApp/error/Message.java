package com.tim.SampleWebApp.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tim.SampleWebApp.common.CommonConstants;
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

	public Message constructFromEnum(ApiMessages apiMessages) {
		Message msg = new Message();
		msg.setCode(apiMessages.getCode());
		msg.setType(apiMessages.getType());
		msg.setDescription(apiMessages.getDescription());

		return msg;
	}

	public Message constructFromEnumForField(ApiMessages apiMessage, String fieldName) {
		Message msg = new Message();
		msg.setCode(apiMessage.getCode());
		msg.setType(apiMessage.getType());
		if (apiMessage.equals(CommonConstants.ApiMessages.FIELD_LENGTH_TOO_LONG)) {
			msg.setDescription(apiMessage.getDescription() + fieldName + ", truncated to database length");
		} else {
			msg.setDescription(apiMessage.getDescription() + fieldName);
		}

		return msg;
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
