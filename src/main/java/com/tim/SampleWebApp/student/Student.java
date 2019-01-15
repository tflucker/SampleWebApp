package com.tim.SampleWebApp.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
@SequenceGenerator(sequenceName="student_seq", name="student_seq", allocationSize=1)
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="student_seq")
	private long id;

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private String email;

	@Column
	private String phoneNumber;

	@Column
	private String studentType;

	public Student() {
		super();
	}

	public Student(long id, String name, String address, String email, String phoneNumber, String studentType) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.studentType = studentType;
	}
	
	public Student(String name, String address, String email, String phoneNumber, String studentType) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.studentType = studentType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", studentType=" + studentType + "]";
	}
	
	

}