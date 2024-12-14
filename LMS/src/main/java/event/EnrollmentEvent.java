package event;

public class EnrollmentEvent {
    private final String email;
    private final String courseName;

    public EnrollmentEvent(String email, String courseName) {
        this.email = email;
        this.courseName = courseName;
    }

    public String getEmail() {
        return email;
    }

    public String getCourseName() {
        return courseName;
    }
}
