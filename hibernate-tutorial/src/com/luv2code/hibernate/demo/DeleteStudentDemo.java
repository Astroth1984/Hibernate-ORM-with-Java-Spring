package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			int studentId = 2;
			
			// New session and start Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve the student based on the Primary key
			System.out.println("\nGetting the student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			// Delete the Student
			session.delete(myStudent);
			
			// Deleting Student id =3
			session.createQuery("delete from Student where id='3'").executeUpdate();
			
			// Commit transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
