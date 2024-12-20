package com.example.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.MediaFile;
import com.example.lms.repository.VirtualDatabase;


@Service
public class CourseManagementService {
    @Autowired
    public VirtualDatabase virtualDatabase;

    public boolean addNewCourse(Course course){
        VirtualDatabase.courses.put(course.getId(), course);
        return  true;
    }
    public Course getCourse(String courseId){
        return  VirtualDatabase.courses.get(courseId);
    }
    public List<Course> getAllCourses(){
        return  new ArrayList<>(VirtualDatabase.courses.values());
    }
    public boolean removeCourse(String courseId){
        VirtualDatabase.courses.remove(courseId);
        return  true;
    }
    public boolean addNewLesson(String courseId , Lesson lesson){
        Course course = virtualDatabase.courses.get(courseId);
        course.addLesson(lesson);
        virtualDatabase.courses.put(courseId, course);
        return  true;
    }
    public Lesson getLesson(String courseId , int lessonNumber){
        return  VirtualDatabase.courses.get(courseId).getLesson(lessonNumber);
    }
    public boolean removeLesson(String courseId ,int lessonNumber){
        VirtualDatabase.courses.get(courseId).removeLesson(lessonNumber);
        return  true;
    }
    public boolean uploadMediaFile(String courseId ,MediaFile mediaFile){
        Course course = VirtualDatabase.courses.get(courseId);
        course.addMediaFile(mediaFile);
        VirtualDatabase.courses.put(courseId, course);
        return  true;
    }
}
