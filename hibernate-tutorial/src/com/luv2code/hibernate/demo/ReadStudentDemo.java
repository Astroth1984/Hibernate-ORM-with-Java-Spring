package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			Student tempStudent = new Student("Daffy", "Duck", "d.daffy@gmail.com");
			
			// Start a transaction
			session.beginTransaction();
			
			
			// Save the Java object
			System.out.println("Saving the Student object");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// Commit the transaction
			session.getTransaction().commit();
			
			// Find the student's Id: Primary key
			System.out.println("Saved Student. Generated id: " + tempStudent.getId());
			
			// New session and start Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve the student based on the Primary key
			System.out.println("\nGetting the student with id: " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get Complete -->" + myStudent.toString());
			
			// Commit transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
