package com.example.lms.entity.MediaFiles;
import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Video extends MediaFile{
    MultipartFile videoFile;

    public Video(MultipartFile videoFile ,String fileName) {
        this.videoFile = videoFile;
        this.setFileName(fileName);
    }

    public MultipartFile getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
    }

    @Override
    public boolean uploadIn(File destination) {
        try {
            this.videoFile.transferTo(destination);
        } catch (IOException ex) {
            return false;
        } 
        return true;
    }
}
