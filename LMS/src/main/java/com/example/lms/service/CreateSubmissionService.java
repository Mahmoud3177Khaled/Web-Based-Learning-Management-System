package com.example.lms.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Course;
import com.example.lms.entity.QuizSubmission;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@Service
public class CreateSubmissionService {
    private int i = 0;

    public boolean createSubmission(int studentid, int courseid, int quizIndex, ArrayList<String> studAnswers) {
        try {

            Course course = VirtualDatabase.courses.get(String.valueOf(courseid));

            // Student student = course.getEnrolledStudents().get(studentid);
            // Student student = new Student(studentid, "", "", "");
            
            // if(i == 0) {
            //     course.addStudent(student);
            //     i++;
            // }

            if(VirtualDatabase.courses.get(String.valueOf(courseid)).getEnrolledStudents().get(studentid) == null) {
                return false;
            }

            QuizSubmission newSubmission = new QuizSubmission(studentid, courseid, quizIndex, studAnswers);

            course.addSubmission(newSubmission);
            VirtualDatabase.courses.put(course.getId(), course);

            return true; // put in course for grading
        } catch (Exception e) {
            return false;
        }
    }
}
