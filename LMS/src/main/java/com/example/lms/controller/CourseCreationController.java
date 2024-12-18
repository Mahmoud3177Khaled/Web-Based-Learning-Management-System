package com.example.lms.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.MediaFile;
import lombok.RequiredArgsConstructor;
import com.example.lms.service.CourseCreationService;


@RestController
@RequestMapping("/courseCreation")
@RequiredArgsConstructor 
public class CourseCreationController {
    private CourseCreationService courseCreationService;

    @PostMapping("/createCourse")
    public boolean createCourse(@RequestBody String id,@RequestBody String tittle,@RequestBody String description,@RequestBody Date startDate,@RequestBody Date endDate){
        courseCreationService.createCourse(id, tittle, description, startDate, endDate);
        return  true;
    }

    @PostMapping("/uploadMediaFile")
    public boolean uploadMediaFile(@RequestBody String courseId ,@RequestBody MediaFile mediaFile){
        courseCreationService.uploadMediaFile(courseId, mediaFile);
        return  true;
    }
}
