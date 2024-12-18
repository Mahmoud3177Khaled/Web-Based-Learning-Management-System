package com.example.lms.entity;

public class writtenQuestion extends Question {
    // private String questionText;
    private String correctAnswer;

    writtenQuestion(String text, String correctAnswer) {
        super(text);
        // this.questionText = text;
        this.correctAnswer = correctAnswer;

    }

    // public void setQuestionText(String text) {
    //     this.questionText = text;
    // }

    // public String getQuestionText() {
    //     return this.questionText;
    // }

    public void setCorrectChoice(String answer) {
        this.correctAnswer = answer;
    }

    public String getAnswer() {
        return this.correctAnswer;
    }

    // public void addChoice(String choice) {
    //     this.choices.add(choice);
    // }

    // public ArrayList<String> getChoices() {
    //     return this.choices;
    // }

}
