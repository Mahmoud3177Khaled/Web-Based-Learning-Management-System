package com.example.lms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import jakarta.persistence.Entity;

// @Entity
public class Course{
    private String id;
    private String tittle;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<MediaFile> mediaFiles = new ArrayList<>();
    private Map<Integer,Lesson> lessons = new HashMap<>();
    private Map<Integer,Student> enrolledStudents = new HashMap<>();
    public Course(String id, String tittle, String description, Date startDate, Date endDate){
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    } 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }
    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }
    public Map<Integer,Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Map<Integer,Lesson> lessons) {
        this.lessons = lessons;
    }
    public Lesson getLesson(int lessonNumber){
        return lessons.get(lessonNumber);
    }
    public void setLesson(Lesson lesson) {
        this.lessons.put(lesson.getLessonNumber(), lesson);
    }
    public Map<Integer,Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    public void setEnrolledStudents( Map<Integer,Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
    public boolean addStudent(Student student){
        this.enrolledStudents.put(student.getId(), student);
        return true;
    }
    public boolean addMediaFile(MediaFile mediaFile){
        this.mediaFiles.add(mediaFile);
        return true;
    }
}
