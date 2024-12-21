package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.OTPGenerator;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AttendanceManagementService {

    public boolean generateLessonOTP(String courseId,int lessonNumber){
        Course course = VirtualDatabase.courses.get(courseId);
        Lesson lesson =course.getLesson(lessonNumber);
        lesson.setOTPAttendanceCode(OTPGenerator.generateOTP());
        course.addLesson(lesson);
        VirtualDatabase.courses.put(courseId, course);
        return true;
    }
    public boolean lessonAttends(String courseId,int lessonNumber,int studentId,String OTPCode){
        Course course = VirtualDatabase.courses.get(courseId);
        Lesson lesson =course.getLesson(lessonNumber);
        if(lesson.getOTPAttendanceCode().equals(OTPCode)){
            lesson.addAttendedStudent(studentId);
            course.addLesson(lesson);
            VirtualDatabase.courses.put(course.getId(), course);
            return true;
        }
        return false;
    }
}
