package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.service.UploadMediaFileService;

import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor 
public class UploadMediaFilesController {
    @Autowired
    private UploadMediaFileService uploadMediaFileService;
    @PostMapping("/MediaFileUpload")
    public Response uploadMediaFile(@RequestParam("userType") String userType,
                                @RequestParam("mediaFile") MultipartFile mediaFile) {
        if (RoleAccessControl.userType(userType, "Instructor")) {
            boolean isUploaded = uploadMediaFileService.Upload(new MediaFile(mediaFile),mediaFile.getOriginalFilename());
            if (isUploaded) {
                return new Response("The mediaFile file uploaded.");
            }
            return new Response("An error occurred while uploading the mediaFile file.");
        }
        return new Response("You don't have authorization.");
    }
}
