package com.example.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

// import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.Course;
import com.example.lms.entity.QuestionBank;
import com.example.lms.entity.Response;
import com.example.lms.entity.mcqQuestion;
import com.example.lms.entity.tfQuestion;
import com.example.lms.entity.writtenQuestion;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AddQuestionToBankService {
    // VirtualDatabase db;
    private int i = 0;
    
    public boolean addQuastionToBank(String courseid, String questionType, String text, List<String> choices, String correctAnswer) { // add bank inside course and get bank from course

        if (i == 0) {
            VirtualDatabase.courses.put("1", new Course("1"));
    
        }
        i++;
        Course courseToAddTo = VirtualDatabase.courses.get(courseid);

        try {
            if(questionType.equals("mcq")) {
                Question newQuestion = new mcqQuestion(text, choices, correctAnswer);
                courseToAddTo.addQuestionToBank(newQuestion);
    
            } else if (questionType.equals("tf")) {
                Question newQuestion = new tfQuestion(text, correctAnswer);
                courseToAddTo.addQuestionToBank(newQuestion);
                
            } else if (questionType.equals("written")) {
                Question newQuestion = new writtenQuestion(text, correctAnswer);
                courseToAddTo.addQuestionToBank(newQuestion);
            }
            
            VirtualDatabase.courses.put(courseToAddTo.getId(), courseToAddTo);
            
            return true;
        } catch(Exception e) {
            return false;
        }
        
    }
    

}
