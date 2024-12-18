package com.example.lms.entity.UserRequest;

import org.springframework.web.multipart.MultipartFile;

public class VideoUploadRequest extends UserRequest {
    MultipartFile videoFile;

    public MultipartFile getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
    }
}
