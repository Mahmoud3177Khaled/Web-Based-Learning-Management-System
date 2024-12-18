package com.example.lms.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.MediaFile;
import com.example.lms.repository.VirtualDatabase;


@Service
public class CourseCreationService {
    public VirtualDatabase virtualDatabase;
    public boolean createCourse(String id, String tittle, String description, Date startDate, Date endDate){
        virtualDatabase.courses.put(id, new Course(id, tittle, description, startDate, endDate));
        return  true;
    }
    public boolean uploadMediaFile(String courseId ,MediaFile mediaFile){
        Course course = virtualDatabase.courses.get(courseId);
        course.addMediaFile(mediaFile);
        virtualDatabase.courses.put(courseId, course);
        return  true;
    }
}
