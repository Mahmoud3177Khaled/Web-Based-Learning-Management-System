package com.example.lms.QuestionBankServiceTests;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.mcqQuestion;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AddQuestionToBankService;

@SpringBootTest
public class AddQuestionToBankServiceTest {

    private AddQuestionToBankService addQuestionToBankService;
    private List<String> choices = new ArrayList<>();

    @BeforeEach
    void setup() {
        VirtualDatabase.courses.put("1", new Course("1"));

        this.addQuestionToBankService = new AddQuestionToBankService();
    }

    @AfterEach
    void cleanUp() {
        VirtualDatabase.courses.remove("1");
    }

    @Test
	void submitQuestionTestSuccess() {
        // List<String> choices = new ArrayList<>();
        boolean success = addQuestionToBankService.addQuastionToBank("1", "tf", "Q1", choices, "1");

        // assertTrue(success);
        assertTrue(VirtualDatabase.courses.get("1").getBank().getQuestions().size() == 1);
	}
    
    @Test
	void submitQuestionTestNoCourseCourseFailure() {
        // List<String> choices = new ArrayList<>();
        boolean success = addQuestionToBankService.addQuastionToBank("2", "tf", "Q1", choices, "1");
        
        // assertTrue(!success);
        assertTrue(VirtualDatabase.courses.get("1").getBank().getQuestions().size() == 0);
	}

    @Test
	void submitQuestionTestmcqQuestion() {
        choices.add("a");
        choices.add("b");
        choices.add("c");
        choices.add("d");
        boolean success = addQuestionToBankService.addQuastionToBank("1", "mcq", "Q1", choices, "33");
        
        // assertTrue(!success);
        assertTrue(((mcqQuestion) VirtualDatabase.courses.get("1").getBank().getQuestions().get(0)).getChoices().size() == 4);
	}


}
