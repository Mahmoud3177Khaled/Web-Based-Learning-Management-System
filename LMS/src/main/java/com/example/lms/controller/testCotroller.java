package com.example.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.lms.repository.VirtualDatabase;
import com.example.lms.entity.Course;
import com.example.lms.entity.Instructor;
import com.example.lms.entity.Lesson;

@RestController
@RequestMapping("/test")
public class testCotroller {
    @Autowired
    private VirtualDatabase virtualDatabase;
    @GetMapping("/t")
    public String test() {
        virtualDatabase.courses.put("1", new Course("1", "intro to cs","no",1,"1-10-2024","30-12-2024"));
        virtualDatabase.courses.put("2", new Course("2", "OOP","no",1,"1-10-2024","30-12-2024"));
        virtualDatabase.courses.put("3", new Course("3", "DS","no",2,"1-10-2024","30-12-2024"));
        virtualDatabase.courses.put("4", new Course("4", "Algo","no",2,"1-10-2024","30-12-2024"));
        virtualDatabase.courses.put("5", new Course("5", "SWE","no",3,"1-10-2024","30-12-2024"));
        virtualDatabase.courses.get("1").addLesson(new Lesson("L1", "no", 1, "1","12-10-2024"));
        virtualDatabase.users.put(1, new Instructor(1,"a","1","a@a.com"));
        virtualDatabase.users.put(2, new Instructor(2,"b","2","a@a.com"));
        virtualDatabase.users.put(3, new Instructor(3,"c","3","a@a.com"));
        return "test ok";
    }
}
