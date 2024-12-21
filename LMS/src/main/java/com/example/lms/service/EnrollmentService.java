package com.example.lms.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@Service
public class EnrollmentService {
    public List<Course> viewAvailableCourses(){
        List<Course> allCourses = new ArrayList<>(VirtualDatabase.courses.values());
        List<Course> availableCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if(course.isAvailable()){
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }
    public boolean enrollInCourse(int studentId , String courseId){
        Student student = VirtualDatabase.students.get(studentId);
        Course enrolledCourse = VirtualDatabase.courses.get(courseId);
        enrolledCourse.addStudent(student);
        VirtualDatabase.courses.put(enrolledCourse.getId(), enrolledCourse);
        return true;
    }
    public List<Student> showEnrolledStudentsIn(String courseId){
        Course searchCourse = VirtualDatabase.courses.get(courseId);
        return new ArrayList<>(searchCourse.getEnrolledStudents().values());
    }
    public Map<Course,Map<Integer,Student>> showEnrolledStudentsInAllCourses(){
        Map<Course,Map<Integer,Student>> allEnrolledStudents =new HashMap<>();
        for (Map.Entry<String, Course> course : VirtualDatabase.courses.entrySet()) {
            allEnrolledStudents.put(course.getValue(), course.getValue().getEnrolledStudents());
        }
        return allEnrolledStudents;
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