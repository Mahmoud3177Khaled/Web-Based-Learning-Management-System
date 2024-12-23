package com.example.lms.NotificationManagmentTests;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.lms.entity.Notification;
import com.example.lms.entity.User;
import com.example.lms.event.Event;
import com.example.lms.event.EventSubscriber;
import com.example.lms.repository.VirtualDatabase;

@SpringBootTest
public class NotificationSubscriberTest implements EventSubscriber {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void handleEvent(Event event) {
        if ("NotificationEvent".equals(event.getType())) {
            int userId = (int) event.getData().get("userId");
            String message = (String) event.getData().get("message");

            // Get the email from VirtualDatabase
            String email = getEmailFromDatabase(userId);

            if (email != null) {
                // Create a new notification object and store it in the virtual database
                VirtualDatabase.notifications
                        .computeIfAbsent(userId, k -> new ArrayList<>())
                        .add(new Notification(userId, message));

                // Send email notification
                sendEmail(email, message);
            } else {
                System.out.println("No email found for User ID " + userId);
            }
        }
    }

    // Method to retrieve the user's email from the VirtualDatabase
    private String getEmailFromDatabase(int userId) {
        User user = VirtualDatabase.students.get(userId);
        if (user == null) {
            user = VirtualDatabase.instructors.get(userId);
        }
        if (user == null) {
            user = VirtualDatabase.admins.get(userId);
        }
        return (user != null) ? user.getEmail() : null;

    }

    // Method to send an email notification
    private void sendEmail(String email, String message) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("New Notification");
        emailMessage.setText(message);

        try {
            mailSender.send(emailMessage);
            System.out.println("Email sent to: " + email + " - Message: " + message);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + email + ": " + e.getMessage());
        }
    }

    @Test
    public void testHandleEvent() {
        // Example test implementation
        Event event = mock(Event.class);
        when(event.getType()).thenReturn("NotificationEvent");
        when(event.getData()).thenReturn(Map.of("userId", 1, "message", "Test message"));

        handleEvent(event);

        // Add assertions to verify the expected behavior
    }
}
