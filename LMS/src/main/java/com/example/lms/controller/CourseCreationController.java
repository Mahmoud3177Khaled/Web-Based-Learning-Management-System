package com.example.lms.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import lombok.RequiredArgsConstructor;
import com.example.lms.service.CourseCreationService;


@RestController
@RequestMapping("/courseCreation")
@RequiredArgsConstructor 
public class CourseCreationController {
    private CourseCreationService courseCreationService;

    @PostMapping("/createCourse")
    public Response createCourse(@RequestBody String userType ,@RequestBody String id,@RequestBody String tittle,@RequestBody String description,@RequestBody Date startDate,@RequestBody Date endDate){
        if(RoleAccessControl.userType(userType, "Instructor")){
            courseCreationService.createCourse(id, tittle, description, startDate, endDate);
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
