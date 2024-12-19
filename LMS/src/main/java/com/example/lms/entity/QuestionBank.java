package com.example.lms.entity;

import java.util.ArrayList;
// import java.util.ArrayList;

public class QuestionBank {
    // Course course;
    ArrayList<Question> questions;

    public QuestionBank(Course course) {
        // this.course = course;
        this.questions = new ArrayList<>();
    }
    
    // public void setCourse(Course course) {
    //     this.course = course;
    // }

    // public Course getCourse() {
    //     return this.course;
    // }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void addQuastion(Question question) {
        this.questions.add(question);
    }

}
