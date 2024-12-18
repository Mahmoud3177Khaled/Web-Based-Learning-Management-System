package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.entity.UserRequest.CourseCreationRequestBody;
import com.example.lms.service.CourseCreationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courseManagement")
@RequiredArgsConstructor 
public class CourseCreationController {
    @Autowired
    private CourseCreationService courseCreationService;

    @PostMapping("/createCourse")
    public Response createCourse(@RequestBody CourseCreationRequestBody creationRequestBody){
        if(RoleAccessControl.userType(creationRequestBody.getUserType(), "Instructor")){
            courseCreationService.createCourse(creationRequestBody.getCourse().getId(), creationRequestBody.getCourse().getTittle(), creationRequestBody.getCourse().getDescription(), creationRequestBody.getCourse().getStartDate(), creationRequestBody.getCourse().getEndDate());
            return  new Response("the Course created.");

        }
        return new Response("you don't have an authorization.");
    }

    @PostMapping("/uploadMediaFile")
    public Response uploadMediaFile(@RequestBody String userType ,@RequestBody String courseId ,@RequestBody MediaFile mediaFile){
        if(RoleAccessControl.userType(userType, "Instructor")){
            courseCreationService.uploadMediaFile(courseId, mediaFile);
            return  new Response("the Media File Uploaded.");

        }
        return  new Response("you don't have an authorization.");
    }
}
