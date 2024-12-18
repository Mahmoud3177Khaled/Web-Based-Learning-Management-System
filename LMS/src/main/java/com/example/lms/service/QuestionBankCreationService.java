package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.QuestionBank;
import com.example.lms.repository.VirtualDatabase;

@Service
public class QuestionBankCreationService {
    VirtualDatabase db;
    
    public boolean createQuestionBank(Course course) {
        try {
            db = new VirtualDatabase();
            QuestionBank bank = new QuestionBank(course);

            db.banks.add(bank);

            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

}
