package com.example.lms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
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
import com.example.lms.service.UploadMediaFileService;

@RestController
@RequestMapping("/assignment")
public class AssignmentCreationController {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;

    @PostMapping("/create")
    public Response createNewAssignment(@RequestParam("assignFile") MultipartFile assignFile, 
                                    @RequestParam("courseid") String courseid, 
                                    @RequestParam("grade") int grade) {
        try {
            MediaFile mediaFile = new MediaFile(assignFile);
            mediaFile.setFileName(assignFile.getOriginalFilename().split(".")[0]);
            mediaFile.setType(assignFile.getOriginalFilename().split(".")[1]);
            mediaFile.setUploadDate(new Date());
            boolean isUploaded = uploadMediaFileService.Upload(mediaFile,courseid);
    
            // if(isUploaded) {
                Assignment newAssignment = new Assignment(mediaFile, grade);
                Course course = VirtualDatabase.courses.get(courseid);
    
                course.addAssignment(newAssignment);
                VirtualDatabase.courses.put(courseid, course);

                return new Response(course, "assignment added");

        } catch(Exception e) {
            return new Response(e.toString());

        }
        // } else {
            // return new Response("failed to uploadfile and assignment");
        // }
    }

    @PostMapping("/submit")
    public Response submitAssignment(@RequestParam("assignment") MultipartFile assignmentSubmissionFile,
                                 @RequestParam("studentid") String studentid, 
                                 @RequestParam("courseid") String courseid, 
                                 @RequestParam("assignmentindex") int assignmentIndex) {
        try {
            MediaFile mediaFile = new MediaFile(assignmentSubmissionFile);
            mediaFile.setFileName(assignmentSubmissionFile.getOriginalFilename().split(".")[0]);
            mediaFile.setType(assignmentSubmissionFile.getOriginalFilename().split(".")[1]);
            mediaFile.setUploadDate(new Date());
            boolean isUploaded = uploadMediaFileService.Upload(mediaFile,courseid);
    
            // if(isUploaded) {
                AssignmentSubmission newAssignmentSubmission = new AssignmentSubmission(mediaFile, studentid, assignmentIndex);
                Course course = VirtualDatabase.courses.get(courseid);
    
                course.addAssignmentSubmission(newAssignmentSubmission);
                VirtualDatabase.courses.put(courseid, course);

                return new Response(course, "assignment added");

        } catch(Exception e) {
            return new Response(e.toString());

        }
    }

    @GetMapping("/grade")
    public Response gradeAssignmentSubmission(@RequestParam("courseid") String courseid,
                                          @RequestParam("assignmentSubmissionIndex") int assignmentSubmissionIndex, 
                                          @RequestParam("gradeToSet") int gradeToSet) {
        
        Course course = VirtualDatabase.courses.get(courseid);

        AssignmentSubmission assignmentSubmissionTograde = course.getAssignmentSubmissions().get(assignmentSubmissionIndex);
        Assignment assignment = course.getAssignments().get(assignmentSubmissionTograde.getAssignmentIndex());

        Student student = VirtualDatabase.students.get(Integer.valueOf(assignmentSubmissionTograde.getStudentid()));

        student.addAssignmentsMark(assignment.getGrade());
        student.addcorrectAssignmentMark(gradeToSet);

        VirtualDatabase.students.put(student.getId(), student);

        return new Response(student, "graded assignmet " + assignmentSubmissionTograde.getAssignmentIndex() + " for student " + assignmentSubmissionTograde.getStudentid());


    }
}
