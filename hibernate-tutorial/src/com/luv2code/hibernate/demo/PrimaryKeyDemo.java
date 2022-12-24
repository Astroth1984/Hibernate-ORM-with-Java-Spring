package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
					
					// Create 3 Java Objects
					System.out.println("Creating 3 Students objects ...");
					Student tempStudent1 = new Student("Paul", "Marck", "p.marck@gmail.com");
					Student tempStudent2 = new Student("Nick", "cave", "nick.cave@gmail.com");
					Student tempStudent3= new Student("black", "sabbath", "darker.black@gmail.com");
					
					// Start a transaction
					session.beginTransaction();
					
					
					// Save the Java object
					System.out.println("Saving the Student object");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					
					// Commit the transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}

	}

}
