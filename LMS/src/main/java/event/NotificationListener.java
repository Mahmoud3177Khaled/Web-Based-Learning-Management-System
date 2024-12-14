package event; // Declares the package name for this class

import service.EmailService; // Imports the EmailService class for sending emails
import org.springframework.beans.factory.annotation.Autowired; // Imports the Autowired annotation for dependency injection
import org.springframework.context.event.EventListener; // Imports the EventListener annotation for handling events
import org.springframework.stereotype.Component; // Imports the Component annotation to mark this class as a Spring component

@Component // Marks this class as a Spring component, allowing it to be detected by component scanning
public class NotificationListener { // Declares the NotificationListener class

    @Autowired // Automatically injects the EmailService bean into this field
    private EmailService emailService; // Declares a private field for EmailService

    @EventListener // Marks this method as an event listener for handling EnrollmentEvent
    public void handleEnrollmentEvent(EnrollmentEvent event) { // Method to handle EnrollmentEvent
        String subject = "Enrollment Confirmation"; // Declares the email subject for enrollment confirmation
        String text = "You have been enrolled in " + event.getCourseName(); // Declares the email text with the course name
        emailService.sendEmail(event.getEmail(), subject, text); // Sends an email using the EmailService with the recipient, subject, and text
    }
}