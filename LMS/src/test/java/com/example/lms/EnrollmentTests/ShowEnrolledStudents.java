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

    @Test
    public void showEnrolledStudentsInAllCoursesTest(){
        Student student1 = new Student(1, "t1", "test","t@t.t");
        Student student2 = new Student(2, "t2", "test","t@t.t");
        Student student3 = new Student(3, "t3", "test","t@t.t");
        Student student4 = new Student(4, "t4", "test","t@t.t");
        Student student5 = new Student(5, "t5", "test","t@t.t");
        Student student6 = new Student(6, "t6", "test","t@t.t");
        Course course1 = new Course("t1", "test", "this course for testing.", 1, "1-1-2024", "1-1-2025");
        Course course2 = new Course("t2", "test", "this course for testing.", 1, "1-1-2024", "1-1-2025");
        Course course3 = new Course("t3", "test", "this course for testing.", 1, "1-1-2024", "1-1-2025");
        Map<Integer , Student> enrolledStudents1 = new HashMap<>();
        Map<Integer , Student> enrolledStudents2 = new HashMap<>();
        Map<Integer , Student> enrolledStudents3 = new HashMap<>();
        enrolledStudents1.put(1, student1);
        enrolledStudents1.put(2, student2);
        enrolledStudents2.put(3, student3);
        enrolledStudents2.put(4, student4);
        enrolledStudents3.put(5, student5);
        enrolledStudents3.put(6, student6);
        course1.setEnrolledStudents(enrolledStudents1);
        course2.setEnrolledStudents(enrolledStudents2);
        course3.setEnrolledStudents(enrolledStudents3);
        VirtualDatabase.students.put(1, student1);
        VirtualDatabase.students.put(2, student2);
        VirtualDatabase.students.put(3, student3);
        VirtualDatabase.students.put(4, student4);
        VirtualDatabase.students.put(5, student5);
        VirtualDatabase.students.put(6, student6);
        VirtualDatabase.courses.put("t1",course1);
        VirtualDatabase.courses.put("t2",course2);
        VirtualDatabase.courses.put("t3",course3);
        Map<Course,Map<Integer,Student>> enrolledStudentsInAllCourse = enrollmentService.showEnrolledStudentsInAllCourses();
        assertTrue(enrolledStudentsInAllCourse.get(course1).containsKey(1));
        assertTrue(enrolledStudentsInAllCourse.get(course1).containsKey(2));
        assertTrue(enrolledStudentsInAllCourse.get(course2).containsKey(3));
        assertTrue(enrolledStudentsInAllCourse.get(course2).containsKey(4));
        assertTrue(enrolledStudentsInAllCourse.get(course3).containsKey(5));
        assertTrue(enrolledStudentsInAllCourse.get(course3).containsKey(6));
        VirtualDatabase.students.remove(1);
        VirtualDatabase.students.remove(2);
        VirtualDatabase.students.remove(3);
        VirtualDatabase.students.remove(4);
        VirtualDatabase.students.remove(5);
        VirtualDatabase.students.remove(6);
        VirtualDatabase.courses.remove("t1");
        VirtualDatabase.courses.remove("t2");
        VirtualDatabase.courses.remove("t3");
    }
}
