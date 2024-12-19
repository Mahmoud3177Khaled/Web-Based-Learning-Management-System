package com.example.lms.entity;

import java.util.ArrayList;

public class QuizSubmission {
    private int quizIndex;
    private int studentid;
    private int courseid;

    ArrayList<String> studAnswers;

    public QuizSubmission(int studentid, int courseid, int quizIndex, ArrayList<String> studAnswers) {
        this.studentid = studentid;
        this.courseid = courseid;
        this.quizIndex = quizIndex;
        this.studAnswers = studAnswers;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudAnswers(ArrayList<String> studAnswers) {
        this.studAnswers = studAnswers;
    }

    public ArrayList<String> getStudAnswers() {
        return studAnswers;
    }

    public void setQuizIndex(int quizIndex) {
        this.quizIndex = quizIndex;
    }

    public int getQuizIndex() {
        return quizIndex;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getCourseid() {
        return courseid;
    }
}
