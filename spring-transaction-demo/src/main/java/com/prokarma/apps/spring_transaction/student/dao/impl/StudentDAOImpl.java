package com.prokarma.apps.spring_transaction.student.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prokarma.apps.spring_transaction.StudentMarks;
import com.prokarma.apps.spring_transaction.student.dao.StudentDAO;
import com.prokarma.apps.spring_transaction.student.helper.StudentRowMapper;

public class StudentDAOImpl implements StudentDAO {
	

	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String name, int age, Integer marks, Integer year) {
		try {
			String empty = null;
			String SQL1 = "insert into Student (name, age) values (?, ?)";
			jdbcTemplateObject.update(SQL1, new Object[]{name,age});

			// Get the latest student id to be used in Marks table
			String SQL2 = "select max(id) from Student";
			int sid = jdbcTemplateObject.queryForInt(SQL2);

			String SQL3 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
			jdbcTemplateObject.update(SQL3, new Object[]{sid,marks, year});
			System.out.println("Created Name = " + name + ", Age = " + age);
			if(name.equalsIgnoreCase("3")){
				System.out.println("Empty string legth : "+empty.length());
			}
//			throw new RuntimeException("simulate Error condition");
		} catch (DataAccessException e) {
			System.out.println("Error in creating record, rolling back");
			throw e;
		}
	}

	public List<StudentMarks> listStudents() {
		String SQL = "select * from Student, Marks where Student.id = Marks.sid";
		List<StudentMarks> studentMarks = jdbcTemplateObject.query(SQL, new StudentRowMapper());

		return studentMarks;
	}

	public void createStudents(List<StudentMarks> studentDetails) {
		long startTime = System.currentTimeMillis();
		String empty = null;
		for (StudentMarks studentMarks : studentDetails) {
			try {
				String SQL1 = "insert into Student (name, age) values (?, ?)";
				jdbcTemplateObject.update(SQL1, new Object[]{studentMarks.getName(),studentMarks.getAge()});
				
				// Get the latest student id to be used in Marks table
				String SQL2 = "select max(id) from Student";
				int sid = jdbcTemplateObject.queryForInt(SQL2);
				
				/*try {
					Thread.sleep(10000l);
				} catch (InterruptedException e) {
					System.out.println("Sleep Intereption exception : " + e);
				} */
				
				String SQL3 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
				jdbcTemplateObject.update(SQL3, new Object[]{sid,studentMarks.getMarks(), studentMarks.getYear()});
				System.out.println("Created Name = " + studentMarks.getName() + ", Age = " + studentMarks.getAge());
				if(studentMarks.getName().equalsIgnoreCase("Name : 3")){
					System.out.println("Empty string legth : "+empty.length());
				}
			} catch (DataAccessException e) {
				System.out.println("Error in creating record, rolling back");
				throw e;
			} catch(NullPointerException n){
				System.out.println("Null pointer exception,");
				throw new NullPointerException();
			}
		}
		System.out.println("Total Time taken to exceite : " + (System.currentTimeMillis() -startTime)/1000 +" s");
	}
}
