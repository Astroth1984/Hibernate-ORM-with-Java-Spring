package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("EagerLazy -> temp Instructor: " + tempInstructor);
			
			
			System.out.println("EagerLazy ->  Courses:" + tempInstructor.getCourses());
			
			
			// Commit the transaction
			session.getTransaction().commit();
			session.close();
			
			System.out.println("\n Session Closed");
			
			System.out.println("EagerLazy ->  Courses:" + tempInstructor.getCourses());
			
				
			System.out.println("EagerLazy -> Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
