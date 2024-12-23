package com.example.lms.AssignmentManagmentTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AssignmentCreationService;
import com.example.lms.service.AssignmentSubmissionService;

@SpringBootTest
public class AssignmentSubmissionTests {

    private AssignmentCreationService assignmentCreationService;

    private AssignmentSubmissionService assignmentSubmissionService;

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

    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
    public void createAssignmentSubmissionSuccess() {

        boolean success = assignmentSubmissionService.submitAssignment(mockFile, "1", "1", 0);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getAssignmentSubmissions().size() == 1);
    }

    @Test
    public void createAssignmentSubmissionNoCourseFailure() {

        boolean success = assignmentSubmissionService.submitAssignment(mockFile, "1", "2", 0);

        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getAssignmentSubmissions().size() == 0);
    }

    @Test
    public void createAssignmentSubmissionNoStudentFailure() {

        boolean success = assignmentSubmissionService.submitAssignment(mockFile, "2", "1", 0);

        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getAssignmentSubmissions().size() == 0);
    }
}
