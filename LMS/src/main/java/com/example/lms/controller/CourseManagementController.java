package com.example.lms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.MediaFile;
import com.example.lms.entity.Response;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;
import com.example.lms.service.CourseManagementService;
import com.example.lms.service.UploadMediaFileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courseManagement")
@RequiredArgsConstructor 
public class CourseManagementController {
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;
    @Autowired
    private CourseManagementService courseManagementService;
    @Autowired
    private UploadMediaFileService uploadMediaFileService;

    @PostMapping("/addNewCourse")
    public Response addNewCourse(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("id") String id,@RequestParam("tittle") String tittle,@RequestParam("description") String description,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
            Course course = new Course(id, tittle, description,userId,startDate,endDate);
            boolean isAdded = courseManagementService.addNewCourse(course);
            if (isAdded) {
                return  new Response("the course created.");
            }
            return  new Response("An error occurred while adding the course.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }
    
    @PostMapping("/removeCourse")
    public Response removeCourse(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
            boolean isRemoved = courseManagementService.removeCourse(userId,courseId);
            if (isRemoved) {
                return  new Response("the course removed.");
            }
            return  new Response("An error occurred while removing the course.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }
    
    @PostMapping("/getCourse")
    public Response getCourse(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
            Course course = courseManagementService.getCourse(userId,courseId);
            if (course != null) {
                return  new Response(course ,"the course is exist.");
            }
            return  new Response("the course not exist.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }

    @PostMapping("/getAllCourses")
    public Response getAllCourses(@RequestParam("userId") int userId ,@RequestParam("password") String password){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
            List<Course> courses = courseManagementService.getAllCourses(userId);
            if (courses != null) {
                return  new Response(courses ,"the course is exist.");
            }
            return  new Response("the course not exist.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }


    @PostMapping("/uploadMediaFile")
    public Response uploadMediaFile(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("userType") String userType,@RequestParam("courseId") String courseId,@RequestParam("mediaFile") MultipartFile multipartFile) {
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
            MediaFile mediaFile = new MediaFile(multipartFile);
            if(multipartFile.getOriginalFilename() == null){
                mediaFile.setFileName("unknown");
                mediaFile.setType(multipartFile.getContentType());
            }
            else{
                int indexOfDot =multipartFile.getOriginalFilename().indexOf('.');
                String name = multipartFile.getOriginalFilename().substring(0, indexOfDot);
                String type = multipartFile.getOriginalFilename().substring(indexOfDot+1);
                mediaFile.setFileName(name);
                mediaFile.setType(type);
            }
            mediaFile.setUploadDate(new Date());
            boolean isUploaded = uploadMediaFileService.Upload(mediaFile,courseId);
            Course course  = VirtualDatabase.courses.get(courseId);
            Boolean isAdded = course.addMediaFile(mediaFile);
            VirtualDatabase.courses.put(courseId, course);
            if (isUploaded && isAdded) {
                return new Response("The mediaFile file uploaded.");
            }
            return new Response("An error occurred while uploading the mediaFile file.");    
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }


    @PostMapping("/addLesson")
    public Response addLesson(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId ,@RequestParam("tittle") String tittle, @RequestParam("description") String description,@RequestParam("lessonNumber") int lessonNumber,@RequestParam("startDateAndTime") String startDateAndTime){
        Lesson lesson = new Lesson(tittle,description,lessonNumber,courseId,startDateAndTime);
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
            boolean isAdded = courseManagementService.addNewLesson(courseId, lesson);
            if(isAdded){
                return  new Response("the lesson added.");
            }
            return new Response("An error occurred while adding the lesson.");    
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }

    @PostMapping("/getLesson")
    public Response getLesson(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId , @RequestParam("lessonNumber") int lessonNumber){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
                Lesson lesson = courseManagementService.getLesson(courseId, lessonNumber);
                if (lesson != null) {
                    return  new Response(lesson ,"the lesson is exist.");
                }
                return  new Response("the lesson not exist.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }

    @PostMapping("/removeLesson")
    public Response removeLesson(@RequestParam("userId") int userId ,@RequestParam("password") String password ,@RequestParam("courseId") String courseId , @RequestParam("lessonNumber") int lessonNumber){
        if(authenticationManagement.isAuthenticate(userId,password)){
            if(authorizationManagement.isAuthorized(userId, "Instructor")){
                boolean isRemoved = courseManagementService.removeLesson(courseId, lessonNumber);
                if (isRemoved) {
                    return  new Response("the lesson removed.");
                }
                return  new Response("An error occurred while removing the lesson.");
            }
            return  new Response("you don't have an authorization.");
        }
        return  new Response("this request need an authentication.");
    }
}

