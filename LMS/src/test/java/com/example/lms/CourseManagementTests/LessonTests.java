package com.example.lms.CourseManagementTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.CourseManagementService;

@SpringBootTest
public class LessonTests {
    @Autowired
    private CourseManagementService courseManagementService;
    
    @Test
    public void createLessonTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        VirtualDatabase.courses.put("t1", newCourse);
        Lesson lesson = new Lesson("l1", "create lesson test.", 1, "t1", "1-1-2024");
        courseManagementService.addNewLesson("t1", lesson);
        Course course = VirtualDatabase.courses.get("t1");
        assertEquals(lesson,course.getLesson(1));
        VirtualDatabase.courses.remove("t1");
    }
    @Test
    public void getLessonTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Lesson newLesson = new Lesson("l1", "create lesson test.", 1, "t1", "1-1-2024");
        newCourse.addLesson(newLesson);
        VirtualDatabase.courses.put("t1", newCourse);
        Lesson lesson = courseManagementService.getLesson("t1", 1);
        assertEquals(newLesson.getLessonNumber(),lesson.getLessonNumber());
        assertEquals(newLesson.getTittle(),lesson.getTittle());
        assertEquals(newLesson.getCourseId(),lesson.getCourseId());
        assertEquals(newLesson.getDescription(),lesson.getDescription());
        assertEquals(newLesson.getStartDateAndTime(),lesson.getStartDateAndTime());
        VirtualDatabase.courses.remove("t1");
    }
    
    @Test
    public void removeLessonTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Lesson newLesson = new Lesson("l1", "create lesson test.", 1, "t1", "1-1-2024");
        newCourse.addLesson(newLesson);
        VirtualDatabase.courses.put("t1", newCourse);
        courseManagementService.removeLesson("t1",1);
        Course course = VirtualDatabase.courses.get("t1");
        assertEquals(null,course.getLesson(1));
        VirtualDatabase.courses.remove("t1");
    }
}
