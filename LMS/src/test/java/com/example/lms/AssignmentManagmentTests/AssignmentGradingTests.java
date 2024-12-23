package com.example.lms.AssignmentManagmentTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.example.lms.entity.AssignmentSubmission;
import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AssignmentCreationService;
import com.example.lms.service.AssignmentGradingService;
import com.example.lms.service.AssignmentSubmissionService;

@SpringBootTest
public class AssignmentGradingTests {
    
    private AssignmentCreationService assignmentCreationService;

    private AssignmentSubmissionService assignmentSubmissionService;

    private AssignmentGradingService assignmentGradingService;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        VirtualDatabase.courses.put("1", new Course("1"));

        Student student = new Student(1, "", "", "");
        VirtualDatabase.courses.get("1").addStudent(student);

        this.mockFile = new MockMultipartFile(
                "assignSubmissionFile", // Parameter name
                "assign1sol.txt", // Original filename
                "text/plain", // MIME type
                "Assignment 1 submission".getBytes() // File content
        );

        this.assignmentCreationService = new AssignmentCreationService();
        assignmentCreationService.createAssignment(mockFile, "1", 5);

        this.assignmentSubmissionService = new AssignmentSubmissionService();
        assignmentSubmissionService.submitAssignment(mockFile, "1", "1", 0);

        this.assignmentGradingService = new AssignmentGradingService();

    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
    public void gradeAssignmentSubmissionSuccess() {

        boolean success = assignmentGradingService.gradeAssignmentSubmission("1", 0, 3);

        // assertTrue(success);

        AssignmentSubmission submission = VirtualDatabase.courses.get("1").getAssignmentSubmissions().get(0);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(Integer.valueOf(submission.getStudentid())).getCorrectAssignmentMarks().get(0) == 3
        && VirtualDatabase.courses.get("1").getEnrolledStudents().get(Integer.valueOf(submission.getStudentid())).getAssignmentsMarks().get(0) == 5);
    }

    @Test
    public void gradeAssignmentSubmissionFailureNoCourse() {

        boolean success = assignmentGradingService.gradeAssignmentSubmission("2", 0, 3);

        // assertTrue(!success);
        AssignmentSubmission submission = VirtualDatabase.courses.get("1").getAssignmentSubmissions().get(0);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(Integer.valueOf(submission.getStudentid())).getCorrectAssignmentMarks().size() == 0
        && VirtualDatabase.courses.get("1").getEnrolledStudents().get(Integer.valueOf(submission.getStudentid())).getAssignmentsMarks().size() == 0);
    }
    
    @Test
    public void gradeAssignmentSubmissionFailureNoSubmission() {
        
        boolean success = assignmentGradingService.gradeAssignmentSubmission("1", 1, 3);
        
        // assertTrue(!success);
        AssignmentSubmission submission = VirtualDatabase.courses.get("1").getAssignmentSubmissions().get(0);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(Integer.valueOf(submission.getStudentid())).getCorrectAssignmentMarks().size() == 0
        && VirtualDatabase.courses.get("1").getEnrolledStudents().get(Integer.valueOf(submission.getStudentid())).getAssignmentsMarks().size() == 0);
    }
}
