package com.example.lms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.Assignment;
import com.example.lms.entity.AssignmentSubmission;
import com.example.lms.entity.Course;
import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;
import com.example.lms.service.AssignmentCreationService;
import com.example.lms.service.AssignmentGradingService;
import com.example.lms.service.AssignmentSubmissionService;
import com.example.lms.service.NotificationService;
import com.example.lms.service.UploadMediaFileService;

// import com.example.lms.security.AuthenticationManagement;
// import com.example.lms.security.AuthorizationManagement;

// @RequestParam("userId") int userId ,@RequestParam("password") String password ,


// if(authenticationManagement.isAuthenticate(userId,password)){
//             if(authorizationManagement.isAuthorized(userId, "Instructor")){
//                      //code
//               }
//  }

@RestController
@RequestMapping("/assignment")
public class AssignmentManagementController {

    @Autowired
    private AssignmentCreationService assignmentCreationService;

    @Autowired
    private AuthenticationManagement authenticationManagement;

    @Autowired
    private AuthorizationManagement authorizationManagement;

    @PostMapping("/create")
    public Response createNewAssignment(@RequestParam("assignFile") MultipartFile assignFile, 
                                    @RequestParam("courseid") String courseid, 
                                    @RequestParam("grade") int grade 
                                    ,@RequestParam("userId") int userId ,
                                    @RequestParam("password") String password
                                    ) {
        try {
            
            if(authenticationManagement.isAuthenticate(userId,password)){
                if(authorizationManagement.isAuthorized(userId, "Instructor")){
                    this.assignmentCreationService = new AssignmentCreationService();

                    boolean success = assignmentCreationService.createAssignment(assignFile, courseid, grade);

                    if(success) {
                        return new Response("Created a new assignment successfully");
                    } else {
                        return new Response("Failed to create a new assignment");

                    }
                        
                
                } else {
                    return new Response("you are not an instructor");
                }
            } else {
                return new Response("invalid credintials");

            }

        } catch(Exception e) {
            return new Response("authntication error");
        }
        }

    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;

    @PostMapping("/submit")
    public Response submitAssignment(@RequestParam("assignment") MultipartFile assignmentSubmissionFile,
                                 @RequestParam("studentid") String studentid, 
                                 @RequestParam("courseid") String courseid, 
                                 @RequestParam("assignmentindex") int assignmentIndex
                                 ,@RequestParam("userId") int userId ,
                                 @RequestParam("password") String password
                                 ) {
        try {

            if(authenticationManagement.isAuthenticate(userId,password)){
                if(authorizationManagement.isAuthorized(userId, "Student")){
                    
                    this.assignmentSubmissionService = new AssignmentSubmissionService();

                    boolean success = assignmentSubmissionService.submitAssignment(assignmentSubmissionFile, studentid, courseid, assignmentIndex);

                    if(success) {
                        return new Response("Submitted an assignment submission successfull");
                    } else {
                        return new Response("Failed to submit an assignment submission");

                    }
                    
                } else {
                    return new Response("you are not a Student");
                }
            } else {
                return new Response("invalid credintials");
                
            }
        } catch(Exception e) {
            return new Response("authntication error");
        }
    }

    @Autowired
    private AssignmentGradingService assignmentGradingService;
    
    @GetMapping("/grade")
    public Response gradeAssignmentSubmission(@RequestParam("courseid") String courseid,
                                          @RequestParam("assignmentSubmissionIndex") int assignmentSubmissionIndex, 
                                          @RequestParam("gradeToSet") int gradeToSet
                                          ,@RequestParam("userId") int userId ,
                                          @RequestParam("password") String password
                                          ) {
        try {

            if(authenticationManagement.isAuthenticate(userId,password)){
                if(authorizationManagement.isAuthorized(userId, "Instructor")){
                
                        this.assignmentGradingService = new AssignmentGradingService();
                        
                        boolean success = assignmentGradingService.gradeAssignmentSubmission(courseid, assignmentSubmissionIndex, gradeToSet);

                        if(success) {
                            return new Response("Graded assignment submission successfully");
                        } else {
                            return new Response("Failed to grade assignment submission ");

                        }

                    // return new Response(student, "graded assignmet " + assignmentSubmissionTograde.getAssignmentIndex() + " for student " + assignmentSubmissionTograde.getStudentid());
                } else {
                    return new Response("you are not an instructor");
                }
            } else {
                return new Response("invalid credintials");

            }
            
        } catch(Exception e) {
            return new Response("authntication error");
        }

    }
}
