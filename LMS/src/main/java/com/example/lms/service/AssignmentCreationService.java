package com.example.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.Assignment;
import com.example.lms.entity.Course;
import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AssignmentCreationService {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;

    public boolean createAssignment(MultipartFile assignFile, String courseid, int grade) {
        try {

            this.uploadMediaFileService = new UploadMediaFileService();

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
                
                // return new Response(course, "assignment added");
                return true;
                
            } else {
                return false;
                // return new Response("assignment not uploaded");

            }

        } catch(Exception e) {
            return false;

        }
    }
}
