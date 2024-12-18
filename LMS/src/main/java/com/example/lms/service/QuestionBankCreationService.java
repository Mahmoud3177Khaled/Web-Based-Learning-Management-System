package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.QuestionBank;
import com.example.lms.repository.VirtualDatabase;

@Service
public class QuestionBankCreationService {
    // VirtualDatabase db;
    
    public boolean createQuestionBank(Course course) {
        try {
            // this.db = db.getInistance();
            QuestionBank bank = new QuestionBank(course);

            VirtualDatabase.banks.add(bank);

            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

}
