package com.example.lms.AssignmentManagmentTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.entity.Course;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AssignmentCreationService;

@SpringBootTest
public class AssignmentCreationTests {

    private AssignmentCreationService assignmentCreationService;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        VirtualDatabase.courses.put("1", new Course("1"));

        this.mockFile = new MockMultipartFile(
                "assignFile", // Parameter name
                "assign1.txt", // Original filename
                "text/plain", // MIME type
                "Assignment 1 ".getBytes() // File content
        );

        this.assignmentCreationService = new AssignmentCreationService();

    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
    public void createAssignmentSuccess() {

        boolean success = assignmentCreationService.createAssignment(mockFile, "1", 5);

        assertTrue(success);
    }

    @Test
    public void createAssignmentNoCourseFailure() {

        boolean success = assignmentCreationService.createAssignment(mockFile, "2", 5);

        assertTrue(!success);
    }
}
