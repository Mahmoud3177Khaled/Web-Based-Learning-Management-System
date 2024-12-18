package com.example.lms.entity;

public class Student {
    private int id;
    private int totalCorrectMarks;
    private int totalQuizMarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void settotalCorrectMarks(int totalCorrectMarks) {
        this.totalCorrectMarks = totalCorrectMarks;
    }

    public int gettotalCorrectMarks() {
        return this.totalCorrectMarks;
    }

    public void setTotalQuizMarks(int totalQuizMarks) {
        this.totalQuizMarks = totalQuizMarks;
    }

    public int getTotalQuizMarks() {
        return this.totalQuizMarks;
    }
}
