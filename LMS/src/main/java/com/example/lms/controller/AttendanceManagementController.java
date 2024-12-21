package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Response;
import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;
import com.example.lms.service.AttendanceManagementService;

@RestController
@RequestMapping("/attendance")
public class AttendanceManagementController {
    @Autowired
    private AttendanceManagementService attendanceManagementService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;

    @PostMapping("/generateLessonOTP")
    public Response generateLessonOTP(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId ,@RequestParam("lessonNumber") int lessonNumber){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
                boolean isGenerated = attendanceManagementService.generateLessonOTP(courseId,lessonNumber);
                if(isGenerated){
                    return  new Response("the code generated.");
                }
                return  new Response("the code not generated.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }

    @PostMapping("/lessonAttends")
    public Response lessonAttends(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId ,@RequestParam("lessonNumber") int lessonNumber,@RequestParam("studentId") int studentId ,@RequestParam("OTPCode") String OTPCode){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Student")){
                boolean isTrueCode = attendanceManagementService.lessonAttends(courseId , lessonNumber,studentId, OTPCode);
                if(isTrueCode){
                    return new Response("the code is true you are attended.");
                }
                return new Response("An error occurred while attending the lesson.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }
}
