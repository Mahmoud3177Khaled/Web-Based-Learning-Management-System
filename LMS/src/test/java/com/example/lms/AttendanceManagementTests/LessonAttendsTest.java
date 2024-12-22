package com.example.lms.AttendanceManagementTests;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lms.entity.Course;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.Student;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.service.AttendanceManagementService;

@SpringBootTest
public class LessonAttendsTest {
    @Autowired
    private AttendanceManagementService attendanceManagementService;

    @Test
    public void lessonAttendsTest(){
        Course newCourse = new Course("t1", "test 1", "test create course function.", 1, "1-1-2024", "1-1-2025");
        Lesson newLesson = new Lesson("l1", "create lesson test.", 1, "t1", "1-1-2024");
        newCourse.addLesson(newLesson);
        VirtualDatabase.courses.put("t1", newCourse);
        attendanceManagementService.generateLessonOTP("t1", 1);
        Student student = new Student(1, "t", "test", "t@t.t");
        VirtualDatabase.students.put(1, student);
        attendanceManagementService.lessonAttends("t1", 1, 1, VirtualDatabase.courses.get("t1").getLesson(1).getOTPAttendanceCode());
        assertNotEquals(true, VirtualDatabase.courses.get("t1").getLesson(1).getAttendedStudentsIds().contains(student));
        VirtualDatabase.courses.remove("t1");
        VirtualDatabase.students.remove(1);
    }
}
