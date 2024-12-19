package com.example.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
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
    public boolean removeCourse(String courseId){
        VirtualDatabase.courses.remove(courseId);
        return  true;
    }
    public boolean uploadMediaFile(String courseId ,MediaFile mediaFile){
        Course course = VirtualDatabase.courses.get(courseId);
        course.addMediaFile(mediaFile);
        VirtualDatabase.courses.put(courseId, course);
        return  true;
    }
}