package com.example.lms.CourseManagementTests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.CourseManagementService;

@SpringBootTest
public class CourseTests {
    @Autowired
    private CourseManagementService courseManagementService;
    
    @Test
    public void createCourseTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        courseManagementService.addNewCourse(newCourse);
        assertTrue(VirtualDatabase.courses.containsKey(newCourse.getId()));
        Course course = VirtualDatabase.courses.get("t1");
        assertEquals("t1",course.getId());
        assertEquals("test 1",course.getTittle());
        assertEquals("test create course function.",course.getDescription());
        assertEquals(1,course.getInstructorId());
        assertEquals("1-1-2024",course.getStartDate());
        assertEquals("1-1-2025",course.getEndDate());
        VirtualDatabase.courses.remove(course.getId());
    }
    @Test
    public void getCourseTest(){
        VirtualDatabase.courses.put("t1", new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025"));
        Course course = courseManagementService.getCourse(1, "t1");
        assertEquals("t1",course.getId());
        assertEquals("test 1",course.getTittle());
        assertEquals("test create course function.",course.getDescription());
        assertEquals(1,course.getInstructorId());
        assertEquals("1-1-2024",course.getStartDate());
        assertEquals("1-1-2025",course.getEndDate());
        VirtualDatabase.courses.remove(course.getId());
    }
    
    @Test
    public void getAllCourseTest(){
        Course course1 = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Course course2 = new Course("t2", "test 2", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Course course3 = new Course("t3", "test 3", "test create course function.", 1, "1-1-2024", "1-1-2025");
        VirtualDatabase.courses.put("t1", course1);
        VirtualDatabase.courses.put("t2", course2);
        VirtualDatabase.courses.put("t3", course3);
        List<Course> courses = courseManagementService.getAllCourses(1);
        assertTrue(courses.contains(course1));    
        assertTrue(courses.contains(course2));    
        assertTrue(courses.contains(course3));    
        VirtualDatabase.courses.remove(course1.getId());
        VirtualDatabase.courses.remove(course2.getId());
        VirtualDatabase.courses.remove(course3.getId());
    }
    @Test
    public void removeCourseTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        VirtualDatabase.courses.put("t1", newCourse);
        courseManagementService.removeCourse(1, "t1");
        assertEquals(false,VirtualDatabase.courses.containsKey("t1"));
    }
}
