package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
			
			int studentId = 1;
			
			// New session and start Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve the student based on the Primary key
			System.out.println("\nGetting the student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Updating Student ...");
			
			myStudent.setFirstName("Scobby");
			
			// Commit transaction
			session.getTransaction().commit();
			
			
			// New Update => New Session => New Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Update email for all students
			System.out.println("Update email for all Students");
			session.createQuery("update Student set email='foo@bar.yahoo'").executeUpdate();
			
			// Commit transaction to Apply changes to Database
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
