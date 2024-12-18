package service;

import java.util.Date;

import org.springframework.stereotype.Service;

import entity.Course;
import entity.MediaFile;
import repository.VirtualDatabase;


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
