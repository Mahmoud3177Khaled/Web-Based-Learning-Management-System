package com.example.lms.QuizManagmentTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.entity.tfQuestion;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.CreateSubmissionService;
import com.example.lms.service.GradeQuizSubmissionService;
import com.example.lms.service.QuizCreationService;

@SpringBootTest
public class QuizGradingTests {

    private QuizCreationService quizCreationService;
    private CreateSubmissionService createSubmissionService;
    private GradeQuizSubmissionService gradeQuizSubmissionService;

    private List<String> studentAnswers = new ArrayList<>();

    @BeforeEach
    void setup() {
        VirtualDatabase.courses.put("1", new Course("1"));

        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q1", "1"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q2", "1"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q3", "1"));

        Student student = new Student(1, "", "", "");
        VirtualDatabase.courses.get("1").addStudent(student);
        // VirtualDatabase.courses.put("1", );


        this.quizCreationService = new QuizCreationService();
        quizCreationService.createQuiz("1", 2);

        studentAnswers.add("1");
        studentAnswers.add("1");
        // studentAnswers.add("1");

        this.createSubmissionService = new CreateSubmissionService();
        createSubmissionService.createSubmission(1, 1, 0, (ArrayList<String>) studentAnswers);

        gradeQuizSubmissionService = new GradeQuizSubmissionService();

    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
    public void gradeQuizSubmissionTestSuccess() {
        boolean success = gradeQuizSubmissionService.gradeQuizSubmission(1, 0);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(1).getCorrectMarks().size() == 1);

    }

    @Test
    public void gradeQuizSubmissionTestSuccessAccurateGrading() {
        boolean success = gradeQuizSubmissionService.gradeQuizSubmission(1, 0);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(1).getCorrectMarks().get(0) == 2);

    }

    @Test
    public void gradeQuizSubmissionTestSuccessSavedTotalQuizmarks() {
        boolean success = gradeQuizSubmissionService.gradeQuizSubmission(1, 0);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(1).getQuizMarks().get(0) == 2);

    }

    @Test
    public void gradeQuizSubmissionTestSuccessAccurateGrading2() {

        studentAnswers.clear();

        studentAnswers.add("1");
        studentAnswers.add("0");

        boolean success = gradeQuizSubmissionService.gradeQuizSubmission(1, 0);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(1).getCorrectMarks().get(0) == 1);

    }

    @Test
    public void gradeQuizSubmissionTestNoCourseFailure() {
        boolean success = gradeQuizSubmissionService.gradeQuizSubmission(2, 0);

        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(1).getCorrectMarks().size() == 0);

    }

    @Test
    public void gradeQuizSubmissionTestSubmissionFailure() {
        boolean success = gradeQuizSubmissionService.gradeQuizSubmission(1, 1);

        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getEnrolledStudents().get(1).getCorrectMarks().size() == 0);

    }
}
