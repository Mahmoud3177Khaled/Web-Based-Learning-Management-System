package com.example.lms.QuizManagmentTests;

// package com.example.lms.QuestionBankServiceTests;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Student;
import com.example.lms.entity.tfQuestion;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AddQuestionToBankService;
import com.example.lms.service.CreateSubmissionService;
import com.example.lms.service.QuizCreationService;

@SpringBootTest
public class QuizSubmissionTests {

    private QuizCreationService quizCreationService;
    private CreateSubmissionService createSubmissionService;

    private List<String> studentAnswers = new ArrayList<>();

    @BeforeEach
    void setup() {
        VirtualDatabase.courses.put("1", new Course("1"));

        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q1", "0"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q2", "0"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q3", "1"));

        Student student = new Student(1, "", "", "");
        VirtualDatabase.courses.get("1").addStudent(student);
        // VirtualDatabase.courses.put("1", );


        this.quizCreationService = new QuizCreationService();
        quizCreationService.createQuiz("1", 2);

        studentAnswers.add("0");
        studentAnswers.add("1");
        studentAnswers.add("1");

        this.createSubmissionService = new CreateSubmissionService();

    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
	void createnewQuizSubmissionTestSuccess() {

        boolean success = createSubmissionService.createSubmission(1, 1, 0, (ArrayList<String>) studentAnswers);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getQuizSubmissions().size() == 1);
	}
    
    @Test
	void createNewQuizSubmissionTestNoCourseFailure() {
        // List<String> choices = new ArrayList<>();
        boolean success = createSubmissionService.createSubmission(1, 2, 0, (ArrayList<String>) studentAnswers);
        
        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getQuizSubmissions().size() == 0);
	}

    @Test
	void createNewQuizSubmissionTestNoStudentFailure() {
        // List<String> choices = new ArrayList<>();
        boolean success = createSubmissionService.createSubmission(2, 1, 0, (ArrayList<String>) studentAnswers);
        
        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getQuizSubmissions().size() == 0);
	}


}

