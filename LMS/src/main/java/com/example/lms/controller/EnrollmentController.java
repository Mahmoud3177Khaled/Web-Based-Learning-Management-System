package com.example.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor 

public class EnrollmentController {

    // public EnrollmentController(Map<String,Course> VirtualDatabase.courses){
    //     this.VirtualDatabase.courses = VirtualDatabase.courses;
    // }
    private EnrollmentService enrollmentService;

    @GetMapping("/viewAvailableCourses")
    public List<Course> viewAvailableCourses(){
        return enrollmentService.viewAvailableCourses();
    }
    
    @PostMapping("/enrollInCourse")
    public boolean enrollInCourse(@RequestBody Student student ,@RequestBody String courseId){
        enrollmentService.enrollInCourse(student, courseId);
        return true;
    }

    @GetMapping("/showEnrolledStudents")
    public List<Student> showEnrolledStudents(@RequestBody Course course){
        return enrollmentService.showEnrolledStudents(course);
    }
}