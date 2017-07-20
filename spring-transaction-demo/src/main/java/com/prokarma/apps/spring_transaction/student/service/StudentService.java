package com.prokarma.apps.spring_transaction.student.service;

import java.util.List;

import com.prokarma.apps.spring_transaction.StudentMarks;

public interface StudentService {
	
	public void createStudent(String name, int age, Integer marks, Integer year);
	
	public void createStudents(List<StudentMarks> studentDetails);
	
	public List<StudentMarks> listAllStudents();

}
