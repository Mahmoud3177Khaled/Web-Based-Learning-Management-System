package com.example.lms.entity;

import java.util.ArrayList;

public class QuestionBank {
    Course course;
    private ArrayList<mcqQuestion> mcqQuestions;
    private ArrayList<tfQuestion> tfQuestions;
    private ArrayList<writtenQuestion> writtenQuestions;

    public QuestionBank(Course course, ArrayList<mcqQuestion> mcqQuestions, ArrayList<tfQuestion> tfQuestions, ArrayList<writtenQuestion> writtenQuestions) {
        this.course = course;
        this.mcqQuestions = mcqQuestions;
        this.tfQuestions = tfQuestions;
        this.writtenQuestions = writtenQuestions;
    }

    
    
}
