package com.example.lms.entity;

public abstract class Question {
    private String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionText(String text) {
        this.questionText = text;
    }

    public String getQuestionText() {
        return this.questionText;
    }

}
