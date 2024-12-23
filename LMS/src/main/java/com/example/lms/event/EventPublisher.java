package com.example.lms.event; // Package declaration

import java.util.ArrayList; // Importing ArrayList
import java.util.HashMap; // Importing HashMap
import java.util.List; // Importing List
import java.util.Map; // Importing Map

public class EventPublisher { // Class declaration
    private Map<String, List<EventSubscriber>> subscribers = new HashMap<>(); // Map to hold subscribers

    public void subscribe(String eventType, EventSubscriber subscriber) { // Method to subscribe to events
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(subscriber); // Add subscriber to the list
    }

    public void publish(Event event) { // Method to publish events
        List<EventSubscriber> eventSubscribers = subscribers.get(event.getType()); // Get subscribers for the event type
        if (eventSubscribers != null) { // Check if there are subscribers
            for (EventSubscriber subscriber : eventSubscribers) { // Iterate through subscribers
                subscriber.handleEvent(event); // Notify each subscriber
            }
        }
    }
}
