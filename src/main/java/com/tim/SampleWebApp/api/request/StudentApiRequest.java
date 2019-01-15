package com.tim.SampleWebApp.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class StudentApiRequest {

	@JsonProperty
	private String name;

	@JsonProperty
	private String address;

	@JsonProperty
	private String email;

	@JsonProperty
	private String phoneNumber;

	@JsonProperty
	private String studentType;

	public StudentApiRequest(String name, String address, String email, String phoneNumber, String studentType) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.studentType = studentType;
	}

	public StudentApiRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	@Override
	public String toString() {
		return "StudentApiRequest [name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", studentType=" + studentType + "]";
	}

}
