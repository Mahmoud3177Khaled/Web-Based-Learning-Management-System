package com.example.lms.AttendanceManagementTests;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AttendanceManagementService;

@SpringBootTest
public class GenerateLessonOTPCodeTest {
    @Autowired
    private AttendanceManagementService attendanceManagementService;

    @Test
    public void generateLessonOTPTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Lesson newLesson = new Lesson("l1", "create lesson test.", 1, "t1", "1-1-2024");
        newCourse.addLesson(newLesson);
        VirtualDatabase.courses.put("t1", newCourse);
        attendanceManagementService.generateLessonOTP("t1", 1);
        assertNotEquals("", VirtualDatabase.courses.get("t1").getLesson(1).getOTPAttendanceCode());
        VirtualDatabase.courses.remove("t1");
    }
}
