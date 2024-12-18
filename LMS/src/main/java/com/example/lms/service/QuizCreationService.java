package com.example.lms.service;

import java.util.ArrayList;
import java.util.Collections;


import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuestionBank;
import com.example.lms.repository.VirtualDatabase;

@Service
public class QuizCreationService {
    // VirtualDatabase db;
    
    public boolean createQuiz(Course course, Integer numOfQuestions) {
        try {
            // this.db = db.getInistance();
            Quiz quiz = new Quiz(course, numOfQuestions);

            ArrayList<Question> quizBank = new ArrayList<>();
            for (QuestionBank bank : VirtualDatabase.banks) {
                if(course.getId().equals(bank.getCourse().getId())) {
                    quizBank = bank.getQuestions();
                }
            }

            Collections.shuffle(quizBank);
            quiz.setquestions((ArrayList<Question>) quizBank.subList(0, numOfQuestions));

            VirtualDatabase.quizes.add(quiz);

            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

}
