package com.example.lms.notification;

import com.example.lms.event.Event;
import com.example.lms.event.EventSubscriber;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.entity.Notification;
import com.example.lms.entity.User; // Assuming User is the superclass for Student, Instructor, Admin
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class NotificationSubscriber implements EventSubscriber {

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
}
