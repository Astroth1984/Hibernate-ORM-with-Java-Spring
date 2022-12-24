package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			
			// Create the Objects
			Instructor tempInstructor = new Instructor("Chad", "Mud", "mud@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail(
						"https://youtube.com/chad.mad", 
						"chess" );
			
			// Associate the objects together
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			Instructor tempInstructorTwo = new Instructor("Zineth", "Luna", "luna@gmail.com");
			InstructorDetail tempInstructorDetailTwo = new InstructorDetail(
						"https://youtube.com/zenith", 
						"Game theory" );
			tempInstructorTwo.setInstructorDetail(tempInstructorDetailTwo);
			
			
			// Start a transaction
			session.beginTransaction();
			
			// Save the Instructor
			/*
			 * Note: This also will save instrictorDetail object
			 *  Due to CascadeType.ALL
			 * 
			 */
			System.out.println("Saving instructor ... "+ tempInstructor);
			System.out.println("Saving instructor ... "+ tempInstructorTwo);
			session.save(tempInstructor);
			session.save(tempInstructorTwo);
						
			
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
