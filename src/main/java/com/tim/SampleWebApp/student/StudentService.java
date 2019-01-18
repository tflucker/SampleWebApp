package com.tim.SampleWebApp.student;

import java.util.List;

public interface StudentService {

	
	public List<Student> findAll();
	
	public Student findById(Long id);

	public Student save(Student student);
		
	public void deleteById(Long id);
	
	

	

}
