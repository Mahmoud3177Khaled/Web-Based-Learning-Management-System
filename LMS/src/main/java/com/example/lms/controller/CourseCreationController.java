package com.example.lms.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.AuthenticationRequest;
import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.entity.UserRequest.CourseCreationRequestBody;
import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;
import com.example.lms.service.CourseCreationService;
import com.example.lms.service.UploadMediaFileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courseManagement")
@RequiredArgsConstructor 
public class CourseCreationController {
    @Autowired
    private CourseCreationService courseCreationService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;
    @Autowired
    private UploadMediaFileService uploadMediaFileService;

    @PostMapping("/createCourse")
    public Response createCourse(@RequestBody CourseCreationRequestBody creationRequestBody){
        if(RoleAccessControl.userType(creationRequestBody.getUserType(), "Instructor")){
            courseCreationService.createCourse(creationRequestBody.getCourse().getId(), creationRequestBody.getCourse().getTittle(), creationRequestBody.getCourse().getDescription(), creationRequestBody.getCourse().getStartDate(), creationRequestBody.getCourse().getEndDate());
            return  new Response("the Course created.");

        }
        return new Response("you don't have an authorization.");
    }

    @PostMapping("/MediaFileUpload")
    public Response uploadMediaFile(@RequestParam("id") int id ,@RequestParam("password") String password ,@RequestParam("userType") String userType,@RequestParam("mediaFile") MultipartFile mediaFile) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(id,password);
        if(authenticationManagement.isAuthenticate(authenticationRequest)){
            if(authorizationManagement.isAuthorized(authenticationRequest, "Instructor")){
                boolean isUploaded = uploadMediaFileService.Upload(new MediaFile(mediaFile),mediaFile.getOriginalFilename());
                if (isUploaded) {
                    return new Response("The mediaFile file uploaded.");
                }
                return new Response("An error occurred while uploading the mediaFile file.");    
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }
}

