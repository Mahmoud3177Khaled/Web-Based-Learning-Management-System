package com.example.lms.entity;

import java.util.ArrayList;

public class Student {
    private int id;
    private ArrayList<Integer> correctMarks;
    private ArrayList<Integer> quizMarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCorrectMark(Integer CorrectMark) {
        this.correctMarks.add(CorrectMark);
    }

    public void addQuizmark(Integer quizMark) {
        this.quizMarks.add(quizMark);
    }
}
