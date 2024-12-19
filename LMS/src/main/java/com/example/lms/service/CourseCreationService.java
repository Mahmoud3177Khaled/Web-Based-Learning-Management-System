package com.example.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.MediaFile;
import com.example.lms.repository.VirtualDatabase;


@Service
public class CourseCreationService {
    @Autowired
    public VirtualDatabase virtualDatabase;
    public boolean createCourse(String id, String tittle, String description, Date startDate, Date endDate){
        VirtualDatabase.courses.put(id, new Course(id, tittle, description, startDate, endDate));
        return  true;
    }
    public boolean uploadMediaFile(String courseId ,MediaFile mediaFile){
        Course course = VirtualDatabase.courses.get(courseId);
        course.addMediaFile(mediaFile);
        VirtualDatabase.courses.put(courseId, course);
        return  true;
    }
}
