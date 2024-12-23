package com.example.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Course;
import com.example.lms.entity.Instructor;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@RestController
@RequestMapping("/test")
public class testCotroller {
    @GetMapping("/t")
    public String test() {
        VirtualDatabase.courses.put("1", new Course("1", "intro to cs","no",1,"1-10-2024","30-12-2024"));
        VirtualDatabase.courses.put("2", new Course("2", "OOP","no",1,"1-10-2024","30-12-2024"));
        VirtualDatabase.courses.put("3", new Course("3", "DS","no",2,"1-10-2024","30-12-2024"));
        VirtualDatabase.courses.put("4", new Course("4", "Algo","no",2,"1-10-2024","30-12-2024"));
        VirtualDatabase.courses.put("5", new Course("5", "SWE","no",3,"1-10-2024","30-12-2024"));
        Course course = VirtualDatabase.courses.get("1");
        course.addLesson(new Lesson("L1", "no", 1, "1","12-10-2024"));
        VirtualDatabase.courses.put("1", course);
        VirtualDatabase.instructors.put(1, new Instructor(1,"a","1","a@a.com"));
        VirtualDatabase.instructors.put(2, new Instructor(2,"b","2","a@a.com"));
        VirtualDatabase.instructors.put(3, new Instructor(3,"c","3","a@a.com"));

        Student student = new Student(4, "x", "123", "123");
        VirtualDatabase.students.put(4, student);

        course.addStudent(student);
        VirtualDatabase.courses.put("1", course);
        // VirtualDatabase.instructors.put(3, new Instructor(3,"c","3","a@a.com"));
        return "test ok";
    }
}
