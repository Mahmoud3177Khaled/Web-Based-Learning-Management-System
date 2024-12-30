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

import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;

@RestController
@RequestMapping("/questionbank")
public class QuestionBankController {
    @Autowired
    private AddQuestionToBankService addQuestionToBankService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;

    @PostMapping("/add")
    public Response addQuestionToBank(@RequestParam("courseid") String courseid,
                                  @RequestParam("questionType") String questionType, 
                                  @RequestParam("questionText") String text, 
                                  @RequestParam("choices") List<String> choices, 
                                //   @RequestParam("correctChoice") Integer correctChoice,
                                  @RequestParam("correctAnswer") String correctAnswer
                                  ,@RequestParam("userId") int userId ,
                                @RequestParam("password") String password
                                  ) {
        try {

            if(authenticationManagement.isAuthenticate(userId,password)){
                if(authorizationManagement.isAuthorized(userId, "Instructor")){
        
                this.addQuestionToBankService = new AddQuestionToBankService();
                boolean success = this.addQuestionToBankService.addQuastionToBank(courseid, questionType, text, choices, correctAnswer);
                
                if(success) {
                    return new Response("added " + questionType + " question to course " + courseid);
                    } else {
                        return new Response("failed to add " + questionType + " question to course " + courseid);
                        
                    }
                    
                } else {
                    return new Response("you are not an instructor");
                }
            } else {
                return new Response("invalid credintials");
                
            }
        } catch(Exception e) {
            return new Response("authntication error");
        }

    }
    
}
