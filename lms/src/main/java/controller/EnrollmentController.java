package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Course;
import entity.Student;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor 

public class EnrollmentController {
    private final Map<String,Course> courses;

    // public EnrollmentController(Map<String,Course> courses){
    //     this.courses = courses;
    // }

    @GetMapping("/viewAvailableCourses")
    public List<Course> viewAvailableCourses(){
        return new ArrayList<>(courses.values());
    }
    
    @PostMapping("/enrollInCourse")
    public boolean enrollInCourse(@RequestBody Student student ,@RequestBody String courseId){
        Course enrolledCourse = courses.get(courseId);
        enrolledCourse.addStudent(student);
        courses.put(enrolledCourse.getId(), enrolledCourse);
        return true;
    }

    @GetMapping("/showEnrolledStudents")
    public List<Student> showEnrolledStudents(@RequestBody Course course){
        Course searchCourse = courses.get(course.getId());
        return new ArrayList<>(searchCourse.getEnrolledStudents().values());
    }
}
