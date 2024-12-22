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
public class AssignmentCreationController {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;

    @PostMapping("/create")
    public Response createNewAssignment(@RequestParam("assignFile") MultipartFile assignFile, 
                                    @RequestParam("courseid") String courseid, 
                                    @RequestParam("grade") int grade 
                                    // ,@RequestParam("userId") int userId ,
                                    // @RequestParam("password") String password
                                    ) {

        // if(authenticationManagement.isAuthenticate(userId,password)){
        //     if(authorizationManagement.isAuthorized(userId, "Instructor")){

                try {
                    MediaFile mediaFile = new MediaFile(assignFile);
                    if(assignFile.getOriginalFilename() == null){
                        mediaFile.setFileName("unknown");
                        mediaFile.setType(assignFile.getContentType());
                    }
                    else{
                        int indexOfDot =assignFile.getOriginalFilename().indexOf('.');
                        String name = assignFile.getOriginalFilename().substring(0, indexOfDot);
                        String type = assignFile.getOriginalFilename().substring(indexOfDot+1);
                        mediaFile.setFileName(name);
                        mediaFile.setType(type);
                    }
                    mediaFile.setUploadDate(new Date());
                    boolean isUploaded = uploadMediaFileService.Upload(mediaFile,courseid);
                    
                    if(isUploaded) {
                        Assignment newAssignment = new Assignment(mediaFile, grade);
                        Course course = VirtualDatabase.courses.get(courseid);
                        
                        course.addAssignment(newAssignment);
                        VirtualDatabase.courses.put(course.getId(), course);
                        
                        return new Response(course, "assignment added");
                        
                    } else {
                        return new Response("assignment not uploaded");

                    }
                        
                    } catch(Exception e) {
                        return new Response(e.toString());
                        
                    }
                    // } else {
                        // return new Response("failed to uploadfile and assignment");
                        // }
                //     } else {
                //         return new Response("you are not an instructor");
                //     }
                // } else {
                //     return new Response("invalid credintials");

                // }
    }

    @PostMapping("/submit")
    public Response submitAssignment(@RequestParam("assignment") MultipartFile assignmentSubmissionFile,
                                 @RequestParam("studentid") String studentid, 
                                 @RequestParam("courseid") String courseid, 
                                 @RequestParam("assignmentindex") int assignmentIndex
                                //  ,@RequestParam("userId") int userId ,
                                //  @RequestParam("password") String password
                                 ) {

        // if(authenticationManagement.isAuthenticate(userId,password)){
        //     if(authorizationManagement.isAuthorized(userId, "Student")){
                try {
                    MediaFile mediaFile = new MediaFile(assignmentSubmissionFile);
                    mediaFile.setFileName(assignmentSubmissionFile.getOriginalFilename().split("\\.")[0]);
                    mediaFile.setType(assignmentSubmissionFile.getOriginalFilename().split("\\.")[1]);
                    mediaFile.setUploadDate(new Date());
                    boolean isUploaded = uploadMediaFileService.Upload(mediaFile,courseid);
                    
                    if(isUploaded) {
                        AssignmentSubmission newAssignmentSubmission = new AssignmentSubmission(mediaFile, studentid, assignmentIndex);
                        Course course = VirtualDatabase.courses.get(courseid);
                        
                        course.addAssignmentSubmission(newAssignmentSubmission);
                        VirtualDatabase.courses.put(course.getId(), course);
                        
                        return new Response(course, "assignment added");
                    } else {
                        return new Response("assignment not submitted");
                    }
                
                } catch(Exception e) {
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
    private NotificationService notificationService;
    
    @GetMapping("/grade")
    public Response gradeAssignmentSubmission(@RequestParam("courseid") String courseid,
                                          @RequestParam("assignmentSubmissionIndex") int assignmentSubmissionIndex, 
                                          @RequestParam("gradeToSet") int gradeToSet
                                        //   ,@RequestParam("userId") int userId ,
                                        //   @RequestParam("password") String password
                                          ) {
        // if(authenticationManagement.isAuthenticate(userId,password)){
        //     if(authorizationManagement.isAuthorized(userId, "Instructor")){
                Course course = VirtualDatabase.courses.get(courseid);
                
                AssignmentSubmission assignmentSubmissionTograde = course.getAssignmentSubmissions().get(assignmentSubmissionIndex);
                Assignment assignment = course.getAssignments().get(assignmentSubmissionTograde.getAssignmentIndex());
                
                Student student = VirtualDatabase.students.get(Integer.valueOf(assignmentSubmissionTograde.getStudentid()));
                
                student.addAssignmentsMark(assignment.getGrade());
                student.addcorrectAssignmentMark(gradeToSet);
                
                VirtualDatabase.students.put(student.getId(), student);

                notificationService.addCustomNotification(student.getId(), "you got " + gradeToSet + " out of " + assignment.getGrade() + " in assignment " + assignmentSubmissionTograde.getAssignmentIndex() + " for course " + courseid);
                
                return new Response(student, "graded assignmet " + assignmentSubmissionTograde.getAssignmentIndex() + " for student " + assignmentSubmissionTograde.getStudentid());
        //     } else {
        //         return new Response("you are not an instructor");
        //     }
        // } else {
        //     return new Response("invalid credintials");

        // }


    }
}
