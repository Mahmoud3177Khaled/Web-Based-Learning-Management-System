package com.example.lms.event; // Declares the package name for this class

public class EnrollmentEvent { // Declares the EnrollmentEvent class
    private final String email; // Declares a final field for the email address
    private final String courseName; // Declares a final field for the course name

    public EnrollmentEvent(String email, String courseName) { // Constructor for EnrollmentEvent
        this.email = email; // Initializes the email field with the provided email
        this.courseName = courseName; // Initializes the courseName field with the provided course name
    }

    public String getEmail() { // Getter method for the email field
        return email; // Returns the email address
    }

    public String getCourseName() { // Getter method for the courseName field
        return courseName; // Returns the course name
    }
}
