package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// Create session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		// Use the Session object to save Java object
		try {
			
			// Start a transaction
			session.beginTransaction();
			
			
			// Query the Students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// Display the students
			displayStudents(theStudents);
			
			// Query all the students : Last Name = 'cave'
			theStudents = session.createQuery("from Student s where s.lastName='cave'").getResultList();
			
			System.out.println("\n\nStudents with Last Name of cave");
			displayStudents(theStudents);
			
			
			// Query students: lastName='Marck' OR  firstName='Daffy'
			theStudents = session.createQuery("from Student s where s.lastName='Marck' OR s.firstName='Daffy'").getResultList();
			
			System.out.println("\n\nStudents with Last Name of Marck OR First Name of Daffy");
			displayStudents(theStudents);
			
			
			// Query Students where email LIKE %gmail.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			
			System.out.println("\n\nStudents with email LIKE %gmail.com");
			displayStudents(theStudents);
			
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student temStudent: theStudents) {
			System.out.println("\n" + temStudent.toString());
		}
	}

}
