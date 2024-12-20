package com.example.lms.entity;

import java.util.List;
// import jakarta.persistence.Entity;

// @Entity
public class Lesson {
    private String tittle;
    private String description;
    private int lessonNumber;
    private String courseId;
    private String startDateAndTime;
    private String OTPAttendanceCode;
    private List<Integer> attendedStudentsIds;

    
    public Lesson(String tittle, String description, int lessonNumber, String courseId, String startDateAndTime) {
        this.tittle = tittle;
        this.description = description;
        this.lessonNumber = lessonNumber;
        this.courseId = courseId;
        this.startDateAndTime = startDateAndTime;
    }
    public List<Integer> getAttendedStudentsIds() {
        return attendedStudentsIds;
    }
    public void setAttendedStudentsIds(List<Integer> attendedStudentsIds) {
        this.attendedStudentsIds = attendedStudentsIds;
    }
    public boolean addAttendedStudent(int studentId){
        this.attendedStudentsIds.add(studentId);
        return true;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getLessonNumber() {
        return lessonNumber;
    }
    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
    public String getStartDateAndTime() {
        return startDateAndTime;
    }
    public void setStartDateAndTime(String startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }
    public String getOTPAttendanceCode() {
        return OTPAttendanceCode;
    }
    public void setOTPAttendanceCode(String oTPAttendanceCode) {
        OTPAttendanceCode = oTPAttendanceCode;
    }
}
