package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.MediaFiles.Video;
import com.example.lms.entity.Response;
import com.example.lms.entity.UserRequest.VideoUploadRequest;
import com.example.lms.service.UploadMediaFileService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor 
public class UploadMediaFilesController {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;
    
    @PostMapping("/videoUpload")
    public Response uploadVideo(VideoUploadRequest videoUploadRequest){
        if(RoleAccessControl.userType(videoUploadRequest.getUserType(), "Instructor")){
            boolean isUploaded = uploadMediaFileService.Upload(new Video(videoUploadRequest.getVideoFile()));
            if(isUploaded){
                return  new Response("the video uploaded.");
            }
            return  new Response("happens an uploading error.");
        }
        return new Response("you don't have an authorization.");
    }
}
