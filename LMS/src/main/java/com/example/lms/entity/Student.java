package com.example.lms.entity;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Integer> correctMarks;
    private ArrayList<Integer> quizMarks;

    public Student (int id, String name,String password,String email) {
        super(id, name, password, email,"Student");
        this.correctMarks = new ArrayList<>();
        this.quizMarks = new ArrayList<>();
    };


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
