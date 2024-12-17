package service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import entity.Course;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private Map<String,Course> courses;
    public List<Course> viewAvailableCourses(){
        return new ArrayList<>(courses.values());
    }
    public boolean enrollInCourse(Student student , String courseId){
        Course enrolledCourse = courses.get(courseId);
        enrolledCourse.addStudent(student);
        courses.put(enrolledCourse.getId(), enrolledCourse);
        return true;
    }
    public List<Student> showEnrolledStudents(Course course){
        Course searchCourse = courses.get(course.getId());
        return new ArrayList<>(searchCourse.getEnrolledStudents().values());
    }
    // @Autowired
    // private ApplicationEventPublisher eventPublisher;

    // public void enrollStudent(Student student, Course course) {
    //     // Enrollment logic (e.g., save to database)

    //     // Publish enrollment event
    //     EnrollmentEvent event = new EnrollmentEvent(student.getEmail(), course.getName());
    //     eventPublisher.publishEvent(event);
    // }
}