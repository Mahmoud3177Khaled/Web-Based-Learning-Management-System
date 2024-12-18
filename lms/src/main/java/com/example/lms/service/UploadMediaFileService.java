package com.example.lms.service;

import java.io.File;

import org.springframework.stereotype.Service;

import  com.example.lms.entity.MediaFiles.MediaFile;

@Service
public class UploadMediaFileService {
    public boolean Upload(MediaFile mediaFile){
        File uploadDir = new File("/upload");
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        boolean isUploaded = mediaFile.uploadIn(new File("/upload/" + mediaFile.getFileName()));
        return isUploaded;
    }
}
