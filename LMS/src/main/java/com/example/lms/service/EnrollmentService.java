package com.example.lms.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@Service
public class EnrollmentService {
    public List<Course> viewAvailableCourses(){
        return new ArrayList<>(VirtualDatabase.courses.values());
    }
    public boolean enrollInCourse(Student student , String courseId){
        Course enrolledCourse = VirtualDatabase.courses.get(courseId);
        enrolledCourse.addStudent(student);
        VirtualDatabase.courses.put(enrolledCourse.getId(), enrolledCourse);
        return true;
    }
    public List<Student> showEnrolledStudents(Course course){
        Course searchCourse = VirtualDatabase.courses.get(course.getId());
        return new ArrayList<>(searchCourse.getEnrolledStudents().values());
    }
    // @Autowired
    // private ApplicationEventPublisher eventPublisher;

    // public void enrollStudent(Student student, Course course) {
    //     // Enrollment logic (e.g., save to database)

    //     // Publish enrollment event
    //     EnrollmentEvent event = new EnrollmentEvent(student.getEmail(), course.getName());
    //     eventPublisher.publishEvent(event);
    // }
}