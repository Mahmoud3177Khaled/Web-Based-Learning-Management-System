package com.example.lms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuestionBank;
import com.example.lms.repository.VirtualDatabase;

@Service
public class QuizCreationService {
    // VirtualDatabase db;
    
    public boolean createQuiz(String courseid, Integer numOfQuestions) {
        try {
            Course courseToAddTo = VirtualDatabase.courses.get(courseid);
            // this.db = db.getInistance();
            
            ArrayList<Question> quizBank = new ArrayList<>();
            quizBank = courseToAddTo.getBank().getQuestions();
            
            Collections.shuffle(quizBank);
            List<Question> detachedQuiz = new ArrayList<>(quizBank.subList(0, Math.min(numOfQuestions, quizBank.size())));
            Quiz quiz = new Quiz(Math.min(numOfQuestions, detachedQuiz.size()));
            quiz.setquestions(detachedQuiz);
            // System.out.println(quiz.toString());
            // courseToAddTo.addQuiz(quiz);

            courseToAddTo.addQuiz(quiz);
            VirtualDatabase.courses.put(courseToAddTo.getId(), courseToAddTo);
                    

            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

}
