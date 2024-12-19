package com.example.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.repository.VirtualDatabase;
// import com.example.lms.service.AddQuestionToBankService;
import com.example.lms.service.QuizCreationService;
import com.example.lms.entity.Course;
// import com.example.lms.entity.Question;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.Response;
// import com.example.lms.entity.mcqQuestion;
// import com.example.lms.entity.tfQuestion;
// import com.example.lms.entity.writtenQuestion;

@RestController
@RequestMapping("/quiz")
public class QuizManagemantController {
    @Autowired
    private QuizCreationService quizCreationService;

    @PostMapping("/create")
    public Response createQuiz(@RequestParam("courseid") String courseid, 
                               @RequestParam("numOfQuestions") Integer numOfQuestions) {

        this.quizCreationService = new QuizCreationService();
        
        Course courseToAddTo = VirtualDatabase.courses.get(courseid);

        try {
            Quiz quiz = quizCreationService.createQuiz(courseToAddTo, numOfQuestions);
            courseToAddTo.addQuiz(quiz);

            return new Response(courseToAddTo.getQuizes(), "added a quiz to course " + courseid);
        } catch (Exception e) {
            return new Response(courseToAddTo.getQuizes(), "failed to add a quiz to course " + courseid);
            // return new Response(e.toString());

        }



    }
    
}
