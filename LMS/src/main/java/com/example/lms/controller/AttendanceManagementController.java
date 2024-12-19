package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.service.AttendanceManagementService;

@RestController
@RequestMapping("/attendance")
public class AttendanceManagementController {
    @Autowired
    private AttendanceManagementService attendanceManagementService;
  

    @GetMapping("/generateLessonOTP")
    public boolean generateLessonOTP(@RequestParam("courseId") String courseId ,@RequestParam("lessonNumber") int lessonNumber){
        attendanceManagementService.generateLessonOTP(courseId,lessonNumber);
        return true;
    }

    @GetMapping("/lessonAttends")
    public boolean lessonAttends(@RequestParam("courseId") String courseId ,@RequestParam("lessonNumber") int lessonNumber,@RequestParam("OTPCode") String OTPCode){
        attendanceManagementService.lessonAttends(courseId , lessonNumber, OTPCode);
        return false;
    }
}
