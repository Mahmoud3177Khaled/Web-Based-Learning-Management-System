package com.example.lms.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.example.lms.entity.MediaFile;

@Service
public class UploadMediaFileService {
    public boolean Upload(MediaFile mediaFile ,String courseId){
        File uploadDir = new File("D:\\Documents\\GitHub\\Web-Based-Learning-Management-System/upload/"+courseId);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        boolean isUploaded = mediaFile.uploadIn(new File("D:\\Documents\\GitHub\\Web-Based-Learning-Management-System/upload/" + courseId +"/" +mediaFile.getFileName() +"."+mediaFile.getType()));
        return isUploaded;
    }
}
