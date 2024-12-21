package com.example.lms.entity;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Integer> correctQuizMarks;
    private ArrayList<Integer> quizMarks;

    private ArrayList<Integer> correctAssignmentMarks;
    private ArrayList<Integer> assignmentsMarks;

    public Student (int id, String name,String password,String email) {
        super(id, name, password, email,"Student");
        this.correctQuizMarks = new ArrayList<>();
        this.quizMarks = new ArrayList<>();

        this.correctAssignmentMarks = new ArrayList<>();
        this.assignmentsMarks = new ArrayList<>();
    };


    public void addCorrectMark(Integer CorrectMark) {
        this.correctQuizMarks.add(CorrectMark);
    }

    public void addQuizmark(Integer quizMark) {
        this.quizMarks.add(quizMark);
    }

    public ArrayList<Integer> getCorrectMarks() {
        return this.correctQuizMarks;
    }

    public ArrayList<Integer> getQuizMarks() {
        return this.quizMarks;
    }

    public void setCorrectAssignmentMarks(ArrayList<Integer> correctAssignmentMarks) {
        this.correctAssignmentMarks = correctAssignmentMarks;
    }

    public ArrayList<Integer> getCorrectAssignmentMarks() {
        return correctAssignmentMarks;
    }

    public void setAssignmentsMarks(ArrayList<Integer> assignmentsMarks) {
        this.assignmentsMarks = assignmentsMarks;
    }

    public ArrayList<Integer> getAssignmentsMarks() {
        return assignmentsMarks;
    }
}
