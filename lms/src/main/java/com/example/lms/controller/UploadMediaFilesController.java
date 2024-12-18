package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.MediaFiles.Video;
import com.example.lms.entity.MediaFiles.Audio;
import com.example.lms.entity.MediaFiles.PDF;
import com.example.lms.entity.Response;
import com.example.lms.entity.UserRequest.VideoUploadRequest;
import com.example.lms.entity.UserRequest.PDFUploadRequest;
import com.example.lms.service.UploadMediaFileService;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor 
public class UploadMediaFilesController {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;
    
    @PostMapping("/videoUpload")
    public Response uploadVideo(@RequestBody VideoUploadRequest videoUploadRequest){
        if(RoleAccessControl.userType(videoUploadRequest.getUserType(), "Instructor")){
            String fileName = (videoUploadRequest.getVideoFile()).getOriginalFilename();
            Video video = new Video(videoUploadRequest.getVideoFile(), fileName);
            boolean isUploaded = uploadMediaFileService.Upload(video,fileName);
            if(isUploaded){
                return  new Response("the video uploaded.");
            }
            return  new Response("happens an uploading error.");
        }
        return new Response("you don't have an authorization.");
    }
    @PostMapping("/PDFUpload")
    public Response uploadPDF(@RequestParam("userType") String userType,@RequestParam("pdfFile") MultipartFile pdfFile){
        if(RoleAccessControl.userType(userType, "Instructor")){
            PDF pdf = new PDF(pdfFile);
            boolean isUploaded = uploadMediaFileService.Upload(pdf,pdfFile.getOriginalFilename());
            if(isUploaded){
                return  new Response("the pdf uploaded.");
            }
            return  new Response("happens an uploading error.");
        }
        return new Response("you don't have an authorization.");
    }
    @PostMapping("/audioUpload")
    public Response uploadAudio(@RequestParam("userType") String userType,
                                @RequestParam("audioFile") MultipartFile audioFile) {
        if (RoleAccessControl.userType(userType, "Instructor")) {
            boolean isUploaded = uploadMediaFileService.Upload(new Audio(audioFile),audioFile.getOriginalFilename());
            if (isUploaded) {
                return new Response("The audio file uploaded.");
            }
            return new Response("An error occurred while uploading the audio file.");
        }
        return new Response("You don't have authorization.");
    }
}
