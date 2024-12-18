package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.QuestionBank;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AddQuestionToBankService {
    VirtualDatabase db;
    
    public boolean addQuastionToBank(Question question, QuestionBank bankInput) {
        try {
            db = new VirtualDatabase();

            for(QuestionBank bank : db.banks) {
                if(bank.getCourse().getId().equals(bankInput.getCourse().getId())) {
                    bank.addQuastion(question);
                }
            }

            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

}
