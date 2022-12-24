package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		// Create session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		// Use the Session object to save Java object
		try {
			
			
			// Start a transaction
			session.beginTransaction();
			
			// Create a course
			Course tempCourse = new Course("Pack man! - How to score 1 Million Points");
			
			// Add some reviews
			tempCourse.addReview(new Review("Great course ... Loved it!"));	
			tempCourse.addReview(new Review("Not bad, but money saver from arcade"));	
			tempCourse.addReview(new Review("Naaaah, i give up"));	
			
			// Save the course ... leverage the cascade all
			System.out.println("Saving the Course ==> " + tempCourse);
			System.out.println("Reviews List ==> "+ tempCourse.getReviews());
			session.save(tempCourse);
			
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
