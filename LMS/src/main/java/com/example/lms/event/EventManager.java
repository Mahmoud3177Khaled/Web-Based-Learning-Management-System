package com.example.lms.event; // Package declaration

public class EventManager { // Class declaration
    private static EventPublisher publisher = new EventPublisher(); // Static instance of EventPublisher

    public static EventPublisher getPublisher() { // Static method to get the publisher
        return publisher; // Return the publisher instance
    }
}
