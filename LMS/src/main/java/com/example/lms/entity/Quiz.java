package com.example.lms.entity;

import java.util.ArrayList;

public class Quiz {
    Course course;
    ArrayList<Question> questions;
    
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

    public Quiz createQuiz() {
        Quiz quiz = new Quiz();
        return quiz;
    }


    


    
}
