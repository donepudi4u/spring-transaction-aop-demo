package com.prokarma.apps.spring_transaction;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prokarma.apps.spring_transaction.student.service.StudentService;

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

      StudentService studentService = (StudentService)context.getBean("studentService");
      
      /*System.out.println("------Records creation--------" );
      studentDao.create("Zara", 12, 99, 2010);
      studentDao.create("Nuha", 21, 97, 2010);
      studentDao.create("Ayan", 24, 100, 2011);*/
      
      List<StudentMarks> students = getStudentDetailsList();

      studentService.createStudents(students);
      
      System.out.println("------Listing all the records--------" );
      List<StudentMarks> studentMarks = studentService.listAllStudents();
      
      for (StudentMarks record : studentMarks) {
         System.out.print("ID : " + record.getId() );
         System.out.print(", Name : " + record.getName() );
         System.out.print(", Marks : " + record.getMarks());
         System.out.print(", Year : " + record.getYear());
         System.out.println(", Age : " + record.getAge());
      }
   }

	private static List<StudentMarks> getStudentDetailsList() {
		List<StudentMarks> marksList = new ArrayList<StudentMarks>();
		for (int i=0; i<5;i++) {
			StudentMarks marks = new StudentMarks();
			marks.setName("Name : "+i);
			marks.setYear(2000+i);
			marks.setAge(30+i);
			marks.setSid(1000+i);
			marks.setMarks(35+i);
			marksList.add(marks);
		}
		return marksList;
	}

}