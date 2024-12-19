package com.example.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.AuthenticationRequest;
import com.example.lms.entity.Course;
import com.example.lms.entity.Response;
import com.example.lms.entity.Student;
import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;
import com.example.lms.service.CourseManagementService;
import com.example.lms.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor 

public class EnrollmentController {
    
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;

    @GetMapping("/viewAvailableCourses")
    public Response viewAvailableCourses(@RequestParam("authenticationRequest") AuthenticationRequest authenticationRequest){
        if(authenticationManagement.isAuthenticate(authenticationRequest)){
            if(authorizationManagement.isAuthorized(authenticationRequest, "Student")){
                List<Course> courses = enrollmentService.viewAvailableCourses();
                if(courses != null){
                    return new Response(courses,"there are courses.");
                }
                return new Response("there are not courses.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
        
    }
    
    @PostMapping("/enrollInCourse")
    public Response enrollInCourse(@RequestParam("authenticationRequest") AuthenticationRequest authenticationRequest,@RequestBody Student student ,@RequestBody String courseId){
        if(authenticationManagement.isAuthenticate(authenticationRequest)){
            if(authorizationManagement.isAuthorized(authenticationRequest, "Student")){
                boolean isEnrolled = enrollmentService.enrollInCourse(student, courseId);
                if(isEnrolled){
                    return new Response("is enrolled successful.");
                }
                return new Response("An error occurred while Enrolling the course.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }

    @GetMapping("/showEnrolledStudents")
    public Response showEnrolledStudentsIn(@RequestParam("authenticationRequest") AuthenticationRequest authenticationRequest,@RequestBody String courseId){
        if(authenticationManagement.isAuthenticate(authenticationRequest)){
            if(authorizationManagement.isAuthorized(authenticationRequest, "Instructor") || authorizationManagement.isAuthorized(authenticationRequest, "Admin")){
                List<Student> enrolledStudents = enrollmentService.showEnrolledStudentsIn(courseId);
                if(enrolledStudents != null){
                    return new Response(enrolledStudents,"there are students enrolled in that course.");
                }
                return new Response("An error occurred while retrieving Enrolling students the course.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }

    @GetMapping("/showEnrolledStudents")
    public Response showEnrolledStudentsInAllCourses(@RequestParam("authenticationRequest") AuthenticationRequest authenticationRequest){
        if(authenticationManagement.isAuthenticate(authenticationRequest)){
            if(authorizationManagement.isAuthorized(authenticationRequest, "Instructor") || authorizationManagement.isAuthorized(authenticationRequest, "Admin")){
                Map<Course,Map<Integer,Student>> enrolledStudentsInAllCourses = enrollmentService.showEnrolledStudentsInAllCourses();
                if(enrolledStudentsInAllCourses != null){
                    return new Response(enrolledStudentsInAllCourses,"there are students enrolled in courses.");
                }
                return new Response("An error occurred while retrieving Enrolling students.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }
}
