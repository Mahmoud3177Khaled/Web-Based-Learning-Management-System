package com.example.lms.CourseManagementTests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Instructor;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.CourseManagementService;

@SpringBootTest
public class CreateCourseTests {
    @Autowired
    private CourseManagementService courseManagementService;
    
    @Test
    public void createCourse(){
        VirtualDatabase.instructors.put(1, new Instructor(1, "t", "test", "t@t.t"));
        Course course = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        courseManagementService.addNewCourse(course);
        assertTrue(VirtualDatabase.courses.containsKey(course.getId()));
        VirtualDatabase.courses.remove(course.getId());
    }
}
