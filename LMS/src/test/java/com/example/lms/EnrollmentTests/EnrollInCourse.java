package com.example.lms.EnrollmentTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.EnrollmentService;

@SpringBootTest
public class EnrollInCourse {
    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    void enrollInCourseTest(){
        Student student = new Student(1, "t", "test","t@t.t");
        Course course = new Course("t1", "test", "this course for testing.", 1, "1-1-2024", "1-1-2025");
        VirtualDatabase.students.put(1, student);
        VirtualDatabase.courses.put("t1",course);
        enrollmentService.enrollInCourse(1, "t1");
        assertTrue(VirtualDatabase.courses.get("t1").getEnrolledStudents().containsKey(1));
        VirtualDatabase.students.remove(1);
        VirtualDatabase.courses.remove("t1");
    }
}
