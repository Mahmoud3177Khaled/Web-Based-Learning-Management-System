package com.example.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/viewAvailableCourses/{userType}")
    public List<Course> viewAvailableCourses(@PathVariable String userType){
        if(RoleAccessControl.userType(userType, "Student")){
            return enrollmentService.viewAvailableCourses();
        }
        return  null;
        
    }
    
    @PostMapping("/enrollInCourse")
    public boolean enrollInCourse(@RequestParam("userType") String userType ,@RequestBody Student student ,@RequestBody String courseId){
        if(RoleAccessControl.userType(userType, "Student")){
            enrollmentService.enrollInCourse(student, courseId);
            return true;
        }
        return false;
    }

    @GetMapping("/showEnrolledStudents/{userType}")
    public List<Student> showEnrolledStudents(@PathVariable String userType ,@RequestBody Course course){
        if(RoleAccessControl.userType(userType, "Instructor")){
            return enrollmentService.showEnrolledStudents(course);
        }
        return  null;
    }
}
