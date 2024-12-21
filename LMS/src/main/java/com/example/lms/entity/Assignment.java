package com.example.lms.entity;

import org.springframework.web.multipart.MultipartFile;

public class Assignment {
    
    MediaFile assignmentFile;
    int grade;

    public Assignment(MultipartFile assignmentFile, int grade) {
        this.assignmentFile = new MediaFile(assignmentFile);
        this.grade = grade;
    }

    public void setAssignmentFile(MediaFile assignmentFile) {
        this.assignmentFile = assignmentFile;
    }

    public MediaFile getAssignmentFile() {
        return assignmentFile;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    
}
