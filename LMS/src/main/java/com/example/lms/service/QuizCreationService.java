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
    
    public Quiz createQuiz(Course course, Integer numOfQuestions) {
        try {
            // this.db = db.getInistance();
            Quiz quiz = new Quiz(numOfQuestions);

            ArrayList<Question> quizBank = new ArrayList<>();
            quizBank = course.getBank().getQuestions();

            Collections.shuffle(quizBank);
            List<Question> detachedQuiz = new ArrayList<>(quizBank.subList(0, Math.min(numOfQuestions, quizBank.size())));
            quiz.setquestions(detachedQuiz);
            // System.out.println(quiz.toString());
            // course.addQuiz(quiz);

            return quiz;
        } catch(Exception e) {
            return null;
        }
        
    }

}
