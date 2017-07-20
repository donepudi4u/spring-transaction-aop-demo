package com.prokarma.apps.spring_transaction.student.dao;

import java.util.List;

import javax.sql.DataSource;

import com.prokarma.apps.spring_transaction.StudentMarks;

public interface StudentDAO {

	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Student and Marks
	 * tables.
	 */
	public void create(String name, int age, Integer marks, Integer year);

	public void createStudents(List<StudentMarks> studentDetails);

	/**
	 * This is the method to be used to list down all the records from the
	 * Student and Marks tables.
	 */
	public List<StudentMarks> listStudents();


}
