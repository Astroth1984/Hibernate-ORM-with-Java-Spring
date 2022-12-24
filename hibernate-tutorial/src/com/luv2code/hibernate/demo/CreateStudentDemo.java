package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			
			// Create Java Object
			System.out.println("Creating Student object ...");
			Student tempStudent = new Student("Othamne", "El mounir", "othmane.elmn@gmail.com");
			
			// Start a transaction
			session.beginTransaction();
			
			
			// Save the Java object
			System.out.println("Saving the Student object");
			session.save(tempStudent);
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
