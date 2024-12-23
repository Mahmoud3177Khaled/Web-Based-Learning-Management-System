package com.example.lms;

import com.example.lms.event.Event;
import com.example.lms.event.EventManager;
import com.example.lms.notification.NotificationSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
		EventManager.getPublisher().subscribe("NotificationEvent", new NotificationSubscriber());

		Event notificationEvent = new Event(
				"NotificationEvent",
				"QuizSystem",
				Map.of("userId", 1, "message", "Your quiz has been graded!")
		);

		EventManager.getPublisher().publish(notificationEvent);
	}

}
