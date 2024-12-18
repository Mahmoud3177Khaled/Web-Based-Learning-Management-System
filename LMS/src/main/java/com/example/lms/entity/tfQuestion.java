package com.example.lms.entity;

public class tfQuestion {
    private String questionText;
    private Integer correctChoice;

    tfQuestion(String text, Integer correctChoice) {
        this.questionText = text;
        this.correctChoice = correctChoice;

    }

    public void setQuestionText(String text) {
        this.questionText = text;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setCorrectChoice(Integer choiceIndex) {
        this.correctChoice = choiceIndex;
    }

    public Integer getCorrectChoice() {
        return this.correctChoice;
    }

    // public void addChoice(String choice) {
    //     this.choices.add(choice);
    // }

    // public ArrayList<String> getChoices() {
    //     return this.choices;
    // }

}