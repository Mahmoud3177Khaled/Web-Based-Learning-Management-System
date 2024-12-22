package com.example.lms.EnrollmentTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.EnrollmentService;

@SpringBootTest
public class ShowEnrolledStudents {
    
    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    public void showEnrolledStudentsInCourseTest(){
        Student student1 = new Student(1, "t1", "test","t@t.t");
        Student student2 = new Student(2, "t2", "test","t@t.t");
        Student student3 = new Student(3, "t3", "test","t@t.t");
        Course course = new Course("t1", "test", "this course for testing.", 1, "1-1-2024", "1-1-2025");
        Map<Integer , Student> enrolledStudents = new HashMap<>();
        enrolledStudents.put(1, student1);
        enrolledStudents.put(2, student2);
        enrolledStudents.put(3, student3);
        course.setEnrolledStudents(enrolledStudents);
        VirtualDatabase.students.put(1, student1);
        VirtualDatabase.students.put(2, student2);
        VirtualDatabase.students.put(3, student3);
        VirtualDatabase.courses.put("t1",course);
        List<Student> enrolledStudentsInCourse = enrollmentService.showEnrolledStudentsIn("t1");
        assertTrue(enrolledStudentsInCourse.contains(student1));
        assertTrue(enrolledStudentsInCourse.contains(student2));
        assertTrue(enrolledStudentsInCourse.contains(student3));
        VirtualDatabase.students.remove(1);
        VirtualDatabase.students.remove(2);
        VirtualDatabase.students.remove(3);
        VirtualDatabase.courses.remove("t1");
    }
}
