package com.example.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.CreateSubmissionService;
import com.example.lms.service.GradeQuizSubmissionService;
// import com.example.lms.service.AddQuestionToBankService;
import com.example.lms.service.QuizCreationService;
import com.example.lms.entity.Course;
// import com.example.lms.entity.Question;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizSubmission;
import com.example.lms.entity.Response;
// import com.example.lms.entity.mcqQuestion;
// import com.example.lms.entity.tfQuestion;
// import com.example.lms.entity.writtenQuestion;
import com.example.lms.entity.Student;

@RestController
@RequestMapping("/quiz")
public class QuizManagemantController {
    @Autowired
    private QuizCreationService quizCreationService;

    @PostMapping("/create")
    public Response createQuiz(@RequestParam("courseid") String courseid, 
                               @RequestParam("numOfQuestions") Integer numOfQuestions) {

        this.quizCreationService = new QuizCreationService();
        
        Course courseToAddTo = VirtualDatabase.courses.get(courseid);

        try {
            Quiz quiz = quizCreationService.createQuiz(courseToAddTo, numOfQuestions);
            courseToAddTo.addQuiz(quiz);
            VirtualDatabase.courses.put(courseToAddTo.getId(), courseToAddTo);

            return new Response(courseToAddTo, "added a quiz to course " + courseid);
        } catch (Exception e) {
            return new Response(courseToAddTo, "failed to add a quiz to course " + courseid);
            // return new Response(e.toString());

        }

    }

    @Autowired
    private CreateSubmissionService createSubmissionService;
    private int i = 0;

    @PostMapping("/submit")
    public Response submitQuizSubmission(@RequestParam("studentid") int studentid,
                           @RequestParam("courseid") int courseid, 
                           @RequestParam("quizindex") int quizIndex, 
                           @RequestParam("studentanswers") ArrayList<String> studentAnswers) {

        this.createSubmissionService = new CreateSubmissionService();
        try {
            Course course = VirtualDatabase.courses.get(String.valueOf(courseid));
            // Student student = course.getEnrolledStudents().get(studentid);
            Student student = new Student(studentid, "", "", "");

            if(i == 0) {
                course.addStudent(student);
                i++;
            }
    
                QuizSubmission newSubmission = this.createSubmissionService.createSubmission(studentid, courseid, quizIndex, studentAnswers);
                course.addSubmission(newSubmission);
                VirtualDatabase.courses.put(course.getId(), course);
    
                return new Response(course, "Added new quiz submission from Student " + studentid + " in course " + courseid);
                
            } catch (Exception e) {
            return new Response(e.toString());

        }

    }

    @Autowired
    private GradeQuizSubmissionService gradeQuizSubmissionService;

    @GetMapping("/grade")
    public Response gradeQuizSubmission(@RequestParam("courseid") int courseid,
                                        @RequestParam("submissionindex") int submissionindex) {

        this.gradeQuizSubmissionService = new GradeQuizSubmissionService();
        Course course = gradeQuizSubmissionService.gradeQuizSubmission(courseid, submissionindex);

        // return new Response(course, "graded quizsub " + submissionindex + " for course " + courseid);
        return new Response(course, "graded quizsub " + submissionindex + " for course " + courseid);



    }
    
}
