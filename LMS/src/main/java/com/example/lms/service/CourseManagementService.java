package com.example.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.MediaFile;
import com.example.lms.repository.VirtualDatabase;


@Service
public class CourseManagementService {


    public boolean addNewCourse(Course course){
        VirtualDatabase.courses.put(course.getId(), course);
        return  true;
    }
    public Course getCourse(int instructorId,String courseId){
        Course course = VirtualDatabase.courses.get(courseId); 
        if( course != null &&  course.getInstructorId() == instructorId){
            return  course;
        }
        return  null;
    }
    public List<Course> getAllCourses(int instructorId){
        List<Course> allCourses = new ArrayList<>(VirtualDatabase.courses.values());
        List<Course> instructorCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if(course.getInstructorId() == instructorId){
                instructorCourses.add(course);
            }
        }
        return  instructorCourses;
    }
    public boolean removeCourse(int instructorId ,String courseId){
        if(VirtualDatabase.courses.get(courseId).getInstructorId() == instructorId){
            VirtualDatabase.courses.remove(courseId);
            return  true;
        }
        return  false;
    }
    public boolean addNewLesson(String courseId , Lesson lesson){
        Course course = VirtualDatabase.courses.get(courseId);
        course.addLesson(lesson);
        VirtualDatabase.courses.put(courseId, course);
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
