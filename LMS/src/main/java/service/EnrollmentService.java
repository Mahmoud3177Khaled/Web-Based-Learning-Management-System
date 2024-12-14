package service;

import entity.Course;
import entity.Student;
import event.EnrollmentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void enrollStudent(Student student, Course course) {
        // Enrollment logic (e.g., save to database)

        // Publish enrollment event
        EnrollmentEvent event = new EnrollmentEvent(student.getEmail(), course.getName());
        eventPublisher.publishEvent(event);
    }
}