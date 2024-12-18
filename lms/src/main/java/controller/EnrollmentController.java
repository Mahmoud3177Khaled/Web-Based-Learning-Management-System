package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Course;
import entity.Student;
import lombok.RequiredArgsConstructor;
import repository.VirtualDatabase;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor 

public class EnrollmentController {

    // public EnrollmentController(Map<String,Course> VirtualDatabase.courses){
    //     this.VirtualDatabase.courses = VirtualDatabase.courses;
    // }

    @GetMapping("/viewAvailableCourses")
    public List<Course> viewAvailableCourses(){
        return new ArrayList<>(VirtualDatabase.courses.values());
    }
    
    @PostMapping("/enrollInCourse")
    public boolean enrollInCourse(@RequestBody Student student ,@RequestBody String courseId){
        Course enrolledCourse = VirtualDatabase.courses.get(courseId);
        enrolledCourse.addStudent(student);
        VirtualDatabase.courses.put(enrolledCourse.getId(), enrolledCourse);
        return true;
    }

    @GetMapping("/showEnrolledStudents")
    public List<Student> showEnrolledStudents(@RequestBody Course course){
        Course searchCourse = VirtualDatabase.courses.get(course.getId());
        return new ArrayList<>(searchCourse.getEnrolledStudents().values());
    }
}
