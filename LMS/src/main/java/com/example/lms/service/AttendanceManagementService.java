package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.OTPGenerator;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AttendanceManagementService {
    @Autowired
    private VirtualDatabase virtualDatabase;


    public boolean generateLessonOTP(String courseId,int lessonNumber){
        Course course = virtualDatabase.courses.get(courseId);
        Lesson lesson =course.getLesson(lessonNumber);
        lesson.setOTPAttendanceCode(OTPGenerator.generateOTP());
        course.addLesson(lesson);
        virtualDatabase.courses.put(courseId, course);
        return true;
    }
    public boolean lessonAttends(String courseId,int lessonNumber,String OTPCode){
        Course course = virtualDatabase.courses.get(courseId);
        Lesson lesson =course.getLesson(lessonNumber);
        if(lesson.getOTPAttendanceCode().equals(OTPCode)){
            return true;
        }
        return false;
    }
}
