package com.prokarma.apps.spring_transaction.student.service.impl;

import java.util.List;

import com.prokarma.apps.spring_transaction.StudentMarks;
import com.prokarma.apps.spring_transaction.student.dao.StudentDAO;
import com.prokarma.apps.spring_transaction.student.service.StudentService;

public class StudentServiceImpl implements StudentService{
	
	private StudentDAO studentDAO ;
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void createStudent(String name, int age, Integer marks, Integer year) {
		getStudentDAO().create(name, age, marks, year);
		
	}

	public List<StudentMarks> listAllStudents() {
		return getStudentDAO().listStudents();
	}

	public void createStudents(List<StudentMarks> studentDetails) {
//		try{
			getStudentDAO().createStudents(studentDetails);
//		}catch(NullPointerException e){
//			System.out.println("Null pointer Handelled Here ");
//		}
	}

}
