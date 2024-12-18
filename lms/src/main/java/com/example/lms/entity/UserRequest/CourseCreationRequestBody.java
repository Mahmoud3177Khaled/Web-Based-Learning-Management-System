package com.example.lms.entity.UserRequest;

import com.example.lms.entity.Course;

public class CourseCreationRequestBody extends UserRequest {
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}


