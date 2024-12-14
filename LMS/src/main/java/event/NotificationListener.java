package event;

import service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleEnrollmentEvent(EnrollmentEvent event) {
        String subject = "Enrollment Confirmation";
        String text = "You have been enrolled in " + event.getCourseName();
        emailService.sendEmail(event.getEmail(), subject, text);
    }
}