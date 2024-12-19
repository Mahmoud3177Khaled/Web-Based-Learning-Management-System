package com.example.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AddQuestionToBankService;
import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.Response;
import com.example.lms.entity.mcqQuestion;
import com.example.lms.entity.tfQuestion;
import com.example.lms.entity.writtenQuestion;

@RestController
@RequestMapping("/questionbank")
public class QuestionBankController {
    @Autowired
    private AddQuestionToBankService addQuestionToBankService;
    private int i = 0;

    @PostMapping("/add")
    public Response addQuestionToBank(@RequestParam("courseid") String courseid,
                                  @RequestParam("questionType") String questionType, 
                                  @RequestParam("questionText") String text, 
                                  @RequestParam("choices") List<String> choices, 
                                  @RequestParam("correctChoice") Integer correctChoice,
                                  @RequestParam("correctAnswer") String correctAnswer
                                  ) {

        this.addQuestionToBankService = new AddQuestionToBankService();
        if (i == 0) {
            VirtualDatabase.courses.put("1", new Course("1"));
            
        }
        i++;
        Course courseToAddTo = VirtualDatabase.courses.get(courseid);

        try {
            if(questionType.equals("mcq")) {
                Question newQuestion = new mcqQuestion(text, choices, correctChoice);
                courseToAddTo.addQuestionToBank(newQuestion);
    
            } else if (questionType.equals("tf")) {
                Question newQuestion = new tfQuestion(text, correctChoice);
                courseToAddTo.addQuestionToBank(newQuestion);
                
            } else if (questionType.equals("written")) {
                Question newQuestion = new writtenQuestion(text, correctAnswer);
                courseToAddTo.addQuestionToBank(newQuestion);
            }
            
            return new Response(courseToAddTo.getBank(), "added " + questionType + " question to course " + courseid);
        } catch (Exception e) {
            return new Response("failed to add " + questionType + " question to course " + courseid);
            // return new Response(e.toString());

        }



    }
    
}
