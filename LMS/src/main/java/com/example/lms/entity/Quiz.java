package com.example.lms.entity;

import java.util.ArrayList;

public class Quiz {
    Course course;
    ArrayList<Question> questions;

    Integer numOfQuestions;

    public Quiz(Course course, Integer numOfQuestions) {
        this.course = course;
        this.numOfQuestions = numOfQuestions;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setquestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
    
    public ArrayList<Question> getquestions() {
        return this.questions;
    }
    
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void setNumOfQuestions(Integer numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public Integer getNumOfQuestions() {
        return this.numOfQuestions;
    }


    


    
}
