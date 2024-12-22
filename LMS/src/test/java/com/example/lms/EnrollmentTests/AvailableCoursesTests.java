package com.example.lms.EnrollmentTests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.EnrollmentService;

@SpringBootTest
public class AvailableCoursesTests {

    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    public void viewAvailableCoursesTest(){
        Course course1 = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Course course2 = new Course("t2", "test 2", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Course course3 = new Course("t3", "test 3", "test create course function.", 1, "1-1-2024", "1-1-2025");
        VirtualDatabase.courses.put("t1", course1);
        VirtualDatabase.courses.put("t2", course2);
        VirtualDatabase.courses.put("t3", course3);
        List<Course> courses = enrollmentService.viewAvailableCourses();
        assertTrue(courses.contains(course1));    
        assertTrue(courses.contains(course2));    
        assertTrue(courses.contains(course3));    
        VirtualDatabase.courses.remove(course1.getId());
        VirtualDatabase.courses.remove(course2.getId());
        VirtualDatabase.courses.remove(course3.getId());
    }

    @Test
    public void viewAvailableCoursesWithUnavailableCoursesTest(){
        Course course1 = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Course course2 = new Course("t2", "test 2", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Course course3 = new Course("t3", "test 3", "test create course function.", 1, "1-1-2024", "1-1-2025");
        course3.setState("closed");
        VirtualDatabase.courses.put("t1", course1);
        VirtualDatabase.courses.put("t2", course2);
        VirtualDatabase.courses.put("t3", course3);
        List<Course> courses = enrollmentService.viewAvailableCourses();
        assertTrue(courses.contains(course1));    
        assertTrue(courses.contains(course2));    
        assertTrue(!courses.contains(course3));    
        VirtualDatabase.courses.remove(course1.getId());
        VirtualDatabase.courses.remove(course2.getId());
        VirtualDatabase.courses.remove(course3.getId());
    }
}
