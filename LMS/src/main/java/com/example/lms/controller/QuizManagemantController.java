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
import com.example.lms.service.NotificationService;
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

import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;

@RestController
@RequestMapping("/quiz")
public class QuizManagemantController {
    @Autowired
    private QuizCreationService quizCreationService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;

    @PostMapping("/create")
    public Response createQuiz(@RequestParam("courseid") String courseid, 
                               @RequestParam("numOfQuestions") Integer numOfQuestions
                            //    ,@RequestParam("userId") int userId ,
                            //    @RequestParam("password") String password
                               ) {

        // if(authenticationManagement.isAuthenticate(userId,password)){
        //     if(authorizationManagement.isAuthorized(userId, "Instructor")){
                this.quizCreationService = new QuizCreationService();
                
                Course courseToAddTo = VirtualDatabase.courses.get(courseid);
                boolean success = quizCreationService.createQuiz(courseToAddTo, numOfQuestions);

                if(success) {
                    return new Response(courseToAddTo, "added a quiz to course " + courseid);
                    
                } else {
                    return new Response(courseToAddTo, "failed to add a quiz to course " + courseid);
                    
                }

        //     } else {
        //         return new Response("you are not an instructor");
        //     }
        // } else {
        //     return new Response("invalid credintials");

        // }

    }

    @Autowired
    private CreateSubmissionService createSubmissionService;
    private int i = 0;
    
    @PostMapping("/submit")
    public Response submitQuizSubmission(@RequestParam("studentid") int studentid,
                                        @RequestParam("courseid") int courseid, 
                                        @RequestParam("quizindex") int quizIndex, 
                                        @RequestParam("studentanswers") ArrayList<String> studentAnswers
                                        // ,@RequestParam("userId") int userId ,
                                        // @RequestParam("password") String password
                           ) {
        // if(authenticationManagement.isAuthenticate(userId,password)){
        //     if(authorizationManagement.isAuthorized(userId, "Student")){
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
        //     } else {
        //         return new Response("you are not a Student");
        //     }
        // } else {
        //     return new Response("invalid credintials");

        // }

    }

    @Autowired
    private GradeQuizSubmissionService gradeQuizSubmissionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/grade")
    public Response gradeQuizSubmission(@RequestParam("courseid") int courseid,
                                        @RequestParam("submissionindex") int submissionindex
                                        // ,@RequestParam("userId") int userId ,
                                        // @RequestParam("password") String password
                                        ) {
        // if(authenticationManagement.isAuthenticate(userId,password)){
        //     if(authorizationManagement.isAuthorized(userId, "Instructor")){
                this.gradeQuizSubmissionService = new GradeQuizSubmissionService();
                Course course = gradeQuizSubmissionService.gradeQuizSubmission(courseid, submissionindex);
                Student student = course.getEnrolledStudents().get(course.getQuizSubmissions().get(submissionindex).getStudentid());

                notificationService.addCustomNotification(student.getId(), "you got " + student.getCorrectMarks().get(student.getCorrectMarks().size()-1) + " out of " + student.getQuizMarks().get(student.getQuizMarks().size()-1) + " for course " + courseid);
                
                return new Response(course, "you got " + student.getCorrectMarks().get(student.getCorrectMarks().size()-1) + " out of " + student.getQuizMarks().get(student.getQuizMarks().size()-1) + " in quiz " + course.getQuizSubmissions().get(submissionindex).getQuizIndex() + " for course " + courseid);
        //     } else {
        //         return new Response("you are not an instructor");
        //     }
        // } else {
        //     return new Response("invalid credintials");

        // }



    }
    
}
