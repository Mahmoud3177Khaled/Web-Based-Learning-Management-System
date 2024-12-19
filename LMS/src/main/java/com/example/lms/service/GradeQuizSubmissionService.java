package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizSubmission;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@Service
public class GradeQuizSubmissionService {
    public void gradeQuizSubmission(QuizSubmission submission) {
        try {
            Course course = VirtualDatabase.courses.get(String.valueOf(submission.getCourseid()));
            Student student = VirtualDatabase.students.get(submission.getStudentid());
            Quiz quiz = course.getQuizes().get(submission.getQuizIndex());

            int score = 0;
            for (int i = 0; i < submission.getStudAnswers().size(); i++) {
                if(quiz.getquestions().get(i).getCorrectAnswer().equals(submission.getStudAnswers().get(i))) {
                    score++;
                }
            }

            student.addCorrectMark(score);
            student.addQuizmark(quiz.getquestions().size());


        } catch (Exception e) {

        }
        
    }
}
