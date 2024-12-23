package com.example.lms.NotificationManagmentTests;

import com.example.lms.event.Event;
import com.example.lms.event.EventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.lms.notification.NotificationSubscriber;

import java.util.Map;

import static org.mockito.Mockito.*;

class EventPublisherTest {

    private EventPublisher eventPublisher;
    private NotificationSubscriber notificationSubscriber;

    @BeforeEach
    void setUp() {
        eventPublisher = new EventPublisher();
        notificationSubscriber = mock(NotificationSubscriber.class); // Using Mockito to mock the subscriber
        eventPublisher.subscribe("NotificationEvent", notificationSubscriber);
    }

    @Test
    void testPublishEventToSubscriber() {
        // Prepare event data
        Map<String, Object> eventData = Map.of("userId", 1, "message", "Your quiz has been graded!");

        // Create an event of type NotificationEvent
        Event notificationEvent = new Event("NotificationEvent", this, eventData);

        // Publish the event
        eventPublisher.publish(notificationEvent);

        // Verify if the subscriber's handleEvent method was called
        verify(notificationSubscriber, times(1)).handleEvent(notificationEvent);
    }

    @Test
    void testNoSubscriberForUnknownEvent() {
        // Create an event type that no subscriber listens to
        Event unknownEvent = new Event("UnknownEvent", this, Map.of("userId", 1, "message", "No subscribers"));

        // Publish the event (No subscribers for "UnknownEvent")
        eventPublisher.publish(unknownEvent);

        // No verification needed here since no subscribers will be notified
        // You could add assertions to check other behaviors, e.g., logging or ignored events
    }
}
