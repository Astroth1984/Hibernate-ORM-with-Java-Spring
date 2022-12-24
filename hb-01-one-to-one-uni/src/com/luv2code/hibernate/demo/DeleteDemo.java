package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// Create session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		// Use the Session object to save Java object
		try {
			
			
			
			// Start a transaction
			session.beginTransaction();
			
			// Get Instructor Object by Id / primary key
			int theId = 2;
			Instructor deletedInstructor = session.get(Instructor.class, theId);
			System.out.println("Found Instructor: " + deletedInstructor);
			
			// Delete Instructor Object
			if(deletedInstructor != null) {
				System.out.println("Deleting...");
				
				// Save the Instructor
				/*
				 * Note: This also will DELETE also instrictorDetail object
				 *  Due to CascadeType.ALL
				 * 
				 */
				session.delete(deletedInstructor); 
			}
			
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
