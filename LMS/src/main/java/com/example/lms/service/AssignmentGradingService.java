package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Assignment;
import com.example.lms.entity.AssignmentSubmission;
import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AssignmentGradingService {
    @Autowired
    private NotificationService notificationService;

    public boolean gradeAssignmentSubmission(String courseid, int assignmentSubmissionIndex, int gradeToSet) {
        try {

            this.notificationService = new NotificationService();

            Course course = VirtualDatabase.courses.get(courseid);
                
            AssignmentSubmission assignmentSubmissionTograde = course.getAssignmentSubmissions().get(assignmentSubmissionIndex);
            Assignment assignment = course.getAssignments().get(assignmentSubmissionTograde.getAssignmentIndex());
            
            Student student = VirtualDatabase.students.get(Integer.valueOf(assignmentSubmissionTograde.getStudentid()));
            
            student.addAssignmentsMark(assignment.getGrade());
            student.addcorrectAssignmentMark(gradeToSet);
            
            VirtualDatabase.students.put(student.getId(), student);

            notificationService.addCustomNotification(student.getId(), "you got " + gradeToSet + " out of " + assignment.getGrade() + " in assignment " + assignmentSubmissionTograde.getAssignmentIndex() + " for course " + courseid);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
