package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.OTPGenerator;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;


@Service
public class AttendanceManagementService {
    @Autowired
    private NotificationService notificationService;
    
    public boolean generateLessonOTP(String courseId,int lessonNumber){
        Course course = VirtualDatabase.courses.get(courseId);
        Lesson lesson =course.getLesson(lessonNumber);
        lesson.setOTPAttendanceCode(OTPGenerator.generateOTP());
        course.addLesson(lesson);
        VirtualDatabase.courses.put(courseId, course);
        for (Student student : course.getEnrolledStudents().values()) {
            notificationService.addCustomNotification(student.getId(), "the OTP code for Lesson "+lessonNumber+" for course "+course.getTittle()+" is: "+lesson.getOTPAttendanceCode());
        }
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
