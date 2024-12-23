package com.example.lms;

import com.example.lms.entity.Student;
import com.example.lms.entity.User;
import com.example.lms.event.Event;
import com.example.lms.event.EventManager;
import com.example.lms.notification.NotificationSubscriber;
import com.example.lms.repository.VirtualDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {
		// Run Spring Boot Application
		ApplicationContext context = SpringApplication.run(LmsApplication.class, args);

		// Subscribe NotificationSubscriber to handle "NotificationEvent" type events
		EventManager.getPublisher().subscribe("NotificationEvent", context.getBean(NotificationSubscriber.class));

		// Add a sample user (Student) to VirtualDatabase with userId = 1 and an email address
		addSampleUser();

		// Create the notification event
		Event notificationEvent = new Event(
				"NotificationEvent",
				"QuizSystem",  // Source of the event
				Map.of("userId", 1, "message", "Your quiz has been graded!")
		);

		// Publish the notification event
		EventManager.getPublisher().publish(notificationEvent);
	}

	// Define a Spring Bean for NotificationSubscriber to be injected by Spring
	@Bean
	public NotificationSubscriber notificationSubscriber() {
		return new NotificationSubscriber();
	}

	// Helper method to add a sample student with email to the VirtualDatabase
	private static void addSampleUser() {
		// Create a new Student with userId 1 and set an email address
		Student student = new Student();
		student.setId(1);
		student.setEmail("student1@example.com");  // Set email address

		// Add the student to the VirtualDatabase
		VirtualDatabase.students.put(1, student);
	}
}
