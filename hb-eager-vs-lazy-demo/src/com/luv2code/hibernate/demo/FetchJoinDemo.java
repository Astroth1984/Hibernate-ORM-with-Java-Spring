package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// Create session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		// Use the Session object to save Java object
		try {
			
		
			
			// Start a transaction
			session.beginTransaction();
			
			int theId=1;
			
			// Hibernate query with HQL
			Query<Instructor> query = session.createQuery("select i from Instructor i " 
															+ "JOIN FETCH i.courses "
															+ "where i.id=:theInstructorId",
															Instructor.class);
			
			// Set param on query
			query.setParameter("theInstructorId", theId);
			
			// Execute query and get Instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("EagerLazy -> temp Instructor: " + tempInstructor);
			
			
			
			
			// Commit the transaction
			session.getTransaction().commit();
			session.close();
			
			System.out.println("\n Session Closed");
			
			System.out.println(tempInstructor.getCourses());
				
			System.out.println("EagerLazy -> Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
