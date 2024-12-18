package com.example.lms.entity;

public class tfQuestion extends Question {
    // private String questionText;
    private Integer correctChoice;

    public tfQuestion(String text, Integer correctChoice) {
        // this.questionText = text;
        super(text);
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

    // public void addChoice(String choice) {
    //     this.choices.add(choice);
    // }

    // public ArrayList<String> getChoices() {
    //     return this.choices;
    // }

}
