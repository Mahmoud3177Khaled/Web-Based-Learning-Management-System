package com.example.lms.entity;

import java.util.List;

public class mcqQuestion extends Question {
    // private String questionText;
    private List<String> choices;
    private Integer correctChoice;

    public mcqQuestion(String text, List<String> choices, Integer correctChoice) {
        // this.questionText = text;
        super(text);
        this.choices = choices;
        this.correctChoice = correctChoice;

    }

    // public void setQuestionText(String text) {
    //     this.questionText = text;
    // }

    // public String getQuestionText() {
    //     return this.questionText;
    // }

    public void setCorrectChoice(Integer choiceIndex) {
        this.correctChoice = choiceIndex;
    }

    public Integer getCorrectChoice() {
        return this.correctChoice;
    }

    public void addChoice(String choice) {
        this.choices.add(choice);
    }

    public List<String> getChoices() {
        return this.choices;
    }

}
