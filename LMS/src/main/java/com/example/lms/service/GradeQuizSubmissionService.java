package com.example.lms.service;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizSubmission;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@Service
public class GradeQuizSubmissionService {
    public Course gradeQuizSubmission(int courseid, int submissionindex) {
        try {
            Course course = VirtualDatabase.courses.get(String.valueOf(courseid));
            QuizSubmission submissionToGrade = course.getQuizSubmissions().get(submissionindex);
            
            // Student student = VirtualDatabase.students.get(submissionToGrade.getStudentid());
            Student student = course.getEnrolledStudents().get(submissionToGrade.getStudentid());
            Quiz quiz = course.getQuizes().get(submissionToGrade.getQuizIndex());

            int score = 0;
            for (int i = 0; i < submissionToGrade.getStudAnswers().size(); i++) {
                if(quiz.getquestions().get(i).getCorrectAnswer().equals(submissionToGrade.getStudAnswers().get(i))) {
                    score++;
                }
            }

            student.addCorrectMark(score);
            student.addQuizmark(quiz.getquestions().size());

            VirtualDatabase.students.put(student.getId(), student);

            return course;
        } catch (Exception e) {
            // return e.toString();
            return null;
            
        }
        
    }
}
