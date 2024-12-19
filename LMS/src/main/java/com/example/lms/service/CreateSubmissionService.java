package com.example.lms.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lms.entity.QuizSubmission;

@Service
public class CreateSubmissionService {
    public QuizSubmission createSubmission(int studentid, int courseid, int quizIndex, ArrayList<String> studAnswers) {
        try {
            QuizSubmission submission = new QuizSubmission(studentid, courseid, quizIndex, studAnswers);

            return submission;
        } catch (Exception e) {
            return null;
        }
    }
}
