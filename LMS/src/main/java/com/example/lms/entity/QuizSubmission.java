package com.example.lms.entity;

import java.util.ArrayList;

public class QuizSubmission {
    private Quiz quiz;
    private Student student;

    ArrayList<Integer> studAnswers;

    public QuizSubmission(Quiz quiz, ArrayList<Integer> studAnswers, Student student) {
        this.quiz = quiz;
        this.studAnswers = studAnswers;
        this.student = student;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudAnswers(ArrayList<Integer> studAnswers) {
        this.studAnswers = studAnswers;
    }

    public ArrayList<Integer> getStudAnswers() {
        return studAnswers;
    }
}
