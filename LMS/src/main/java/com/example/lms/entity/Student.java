package com.example.lms.entity;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Integer> correctMarks;
    private ArrayList<Integer> quizMarks;


    public void addCorrectMark(Integer CorrectMark) {
        this.correctMarks.add(CorrectMark);
    }

    public void addQuizmark(Integer quizMark) {
        this.quizMarks.add(quizMark);
    }

    public ArrayList<Integer> getCorrectMarks() {
        return this.correctMarks;
    }

    public ArrayList<Integer> getQuizMarks() {
        return this.quizMarks;
    }
}
