package com.example.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Course;
import com.example.lms.entity.QuizSubmission;
import com.example.lms.entity.Response;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.CreateSubmissionService;

@RestController
@RequestMapping("/quiz")
public class SubmitQuizAnswersController {
    @Autowired
    private CreateSubmissionService createSubmissionService;
    private int i = 0;

    @PostMapping("/submit")
    public Response submitQuiz(@RequestParam("studentid") int studentid,
                           @RequestParam("courseid") int courseid, 
                           @RequestParam("quizindex") int quizIndex, 
                           @RequestParam("studentanswers") ArrayList<String> studentAnswers) {

        this.createSubmissionService = new CreateSubmissionService();
        try {
            Course course = VirtualDatabase.courses.get(String.valueOf(courseid));
            // Student student = course.getEnrolledStudents().get(studentid);
            Student student = new Student(studentid, "", "", "");

            if(i == 0) {
                course.addStudent(student);
            }
    
            if(student != null) {
                QuizSubmission newSubmission = this.createSubmissionService.createSubmission(studentid, courseid, quizIndex, studentAnswers);
                course.addSubmission(newSubmission);
    
                return new Response(course, "Added new quiz submission from Student " + studentid + " in course " + courseid);
    
            } else {
                return new Response(course, "Student " + null + " is not registered in course " + courseid);
            }
                
            } catch (Exception e) {
            return new Response(e.toString());

        }

    }
}
