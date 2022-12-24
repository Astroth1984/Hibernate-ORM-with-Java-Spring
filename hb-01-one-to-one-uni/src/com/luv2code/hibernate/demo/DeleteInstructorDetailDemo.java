package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			// Get Instructor Detail object
			int theId = 3;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			// Print the Instructor Detail
			System.out.println("Instructor Detail : " + instructorDetail);
			
			// Print the Associated Instructor
			System.out.println("the associated instructor: " + instructorDetail.getInstructor());
			
			if(instructorDetail != null) {
				// To remove details with out instructor object
				// we need to break the link
				instructorDetail.getInstructor().setInstructorDetail(null);
				
				session.delete(instructorDetail);
			}
			

			// Commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			
			// Handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
