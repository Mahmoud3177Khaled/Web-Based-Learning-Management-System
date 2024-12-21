package com.example.lms.entity;

import org.springframework.web.multipart.MultipartFile;

public class AssignmentSubmission {
    
    MediaFile assignmentFile;
    int studentid;
    // int grade;

    public AssignmentSubmission(MultipartFile assignmentFile, int studentid) {
        this.assignmentFile = new MediaFile(assignmentFile);
        this.studentid = studentid;
    }

    public void setAssignmentFile(MediaFile assignmentFile) {
        this.assignmentFile = assignmentFile;
    }

    public MediaFile getAssignmentFile() {
        return assignmentFile;
    }

    // public void setGrade(int grade) {
    //     this.grade = grade;
    // }

    // public int getGrade() {
    //     return grade;
    // }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getStudentid() {
        return studentid;
    }

    
}
