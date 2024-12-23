package com.example.lms.event; // Package declaration

import java.util.Map; // Importing Map

public class Event { // Class declaration
    private String type; // Event type
    private Object source; // Source of the event
    private long timestamp; // Timestamp of the event
    private Map<String, Object> data; // Data associated with the event

    public Event(String type, Object source, Map<String, Object> data) { // Constructor
        this.type = type; // Set event type
        this.source = source; // Set event source
        this.timestamp = System.currentTimeMillis(); // Set current timestamp
        this.data = data; // Set event data
    }

    public String getType() { // Getter for event type
        return type; // Return event type
    }

    public Object getSource() { // Getter for event source
        return source; // Return event source
    }

    public long getTimestamp() { // Getter for timestamp
        return timestamp; // Return timestamp
    }

    public Map<String, Object> getData() { // Getter for event data
        return data; // Return event data
    }
}
