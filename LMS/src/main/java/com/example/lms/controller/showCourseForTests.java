package com.example.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Course;
import com.example.lms.entity.Instructor;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.Response;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@RestController
@RequestMapping("/test")
public class showCourseForTests {
    @GetMapping("/course")
    public Response test(@RequestParam("courseid") String courseid) {
       Course c = VirtualDatabase.courses.get(courseid);
       return new Response(c, "done");
    }
}
