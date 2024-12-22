package com.example.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.AssignmentSubmission;
import com.example.lms.entity.Course;
import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AssignmentSubmissionService {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;
    
    public boolean submitAssignment(MultipartFile assignmentSubmissionFile, String studentid, String courseid, int assignmentIndex) {
        try {

            this.uploadMediaFileService = new UploadMediaFileService();
            
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
                
                
                return true;
            } else {
                return false;
                
            }

        } catch (Exception e) {
            return false;
        }
    }
}
