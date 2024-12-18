package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuestionBank;
import com.example.lms.repository.VirtualDatabase;

@Service
public class QuizCreationService {
    VirtualDatabase db;
    
    public boolean createQuiz(Course course) {
        try {
            this.db = db.getInistance();
            Quiz quiz = new Quiz(course);

            db.quizes.add(quiz);

            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

}
