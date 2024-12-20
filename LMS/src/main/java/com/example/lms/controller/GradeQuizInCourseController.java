package com.example.lms.controller;

// import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Course;
import com.example.lms.entity.Response;
import com.example.lms.service.GradeQuizSubmissionService;

@RestController
@RequestMapping("/quiz")
public class GradeQuizInCourseController {
    @Autowired
    private GradeQuizSubmissionService gradeQuizSubmissionService;

    @GetMapping("/gradequiz")
    public Response gradeQuizSubmission(@RequestParam("courseid") int courseid,
                                        @RequestParam("submissionindex") int submissionindex) {

        this.gradeQuizSubmissionService = new GradeQuizSubmissionService();
        Course course = gradeQuizSubmissionService.gradeQuizSubmission(courseid, submissionindex);

        // return new Response(course, "graded quizsub " + submissionindex + " for course " + courseid);
        return new Response(course, "graded quizsub " + submissionindex + " for course " + courseid);



    }
}
