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
import com.example.lms.entity.tfQuestion;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AddQuestionToBankService;
import com.example.lms.service.QuizCreationService;

@SpringBootTest
public class QuizCreationTests {

    private QuizCreationService quizCreationService;
    // private List<String> choices = new ArrayList<>();

    @BeforeEach
    void setup() {
        VirtualDatabase.courses.put("1", new Course("1"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q1", "0"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q2", "0"));
        VirtualDatabase.courses.get("1").addQuestionToBank(new tfQuestion("Q3", "1"));

        this.quizCreationService = new QuizCreationService();
    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
	void createnewQuizTestSuccess() {
        // List<String> choices = new ArrayList<>();
        boolean success = quizCreationService.createQuiz("1", 2);

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getQuizes().size() == 1);
	}
    
    @Test
	void createNewQuizTestNoCourseFailure() {
        // List<String> choices = new ArrayList<>();
        boolean success = quizCreationService.createQuiz("2", 2);
        
        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getQuizes().size() == 0);
	}

    @Test
	void createNewQuizNumOfQuestionsTest() {
        // List<String> choices = new ArrayList<>();
        boolean success = quizCreationService.createQuiz("1", 2);
        
        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getQuizes().get(0).getNumOfQuestions() == 2);
	}

    


}

