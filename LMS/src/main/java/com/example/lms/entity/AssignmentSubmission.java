package com.example.lms.entity;

import org.springframework.web.multipart.MultipartFile;

public class AssignmentSubmission {
    
    // MediaFile assignmentFile;
    MediaFile assignmentFile;
    String studentid;
    int assignmentIndex;
    // String grade;

    public AssignmentSubmission(MediaFile assignmentFile, String studentid, int assignmentIndex) {
        this.assignmentFile = assignmentFile;
        this.studentid = studentid;
        this.assignmentIndex = assignmentIndex;
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

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setAssignmentIndex(int assignmentIndex) {
        this.assignmentIndex = assignmentIndex;
    }

    public int getAssignmentIndex() {
        return assignmentIndex;
    }

    
}
