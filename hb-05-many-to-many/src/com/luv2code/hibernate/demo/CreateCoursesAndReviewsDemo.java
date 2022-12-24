package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		// Create session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		// Use the Session object to save Java object
		try {
			
			
			// Start a transaction
			session.beginTransaction();
			
			// Create a course
			Course tempCourse = new Course("Pack man! - How to score 1 Million Points");
			
			System.out.println("\n Saving the course ....");
			session.save(tempCourse);
			System.out.println("Saved Course ==> " + tempCourse);
			
			// Create the Students
			Student student1 = new Student("John", "Dow", "John@yahoo.fr");
			Student student2 = new Student("Mary", "Lee", "mly@gmail.com");
			Student student3 = new Student("Liz", "Fetch", "raven012@msn.com");
			
			// Add the Students to the Course
			tempCourse.addStudent(student1);
			tempCourse.addStudent(student2);
			tempCourse.addStudent(student3);
			
			// Save the Students
			System.out.println("\n Saving Student ...");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			System.out.println("Saving students is complete ==>" + tempCourse.getStudents());
			
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
