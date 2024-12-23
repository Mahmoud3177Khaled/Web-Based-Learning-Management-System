package com.example.lms.NotificationManagmentTests;

import com.example.lms.event.Event;
import com.example.lms.event.EventPublisher;
import com.example.lms.notification.NotificationSubscriber;
import com.example.lms.entity.Notification;
import com.example.lms.repository.VirtualDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class SubscriptionTest {

    private EventPublisher eventPublisher;
    private NotificationSubscriber notificationSubscriber;

    @BeforeEach
    void setUp() {
        // Initialize EventPublisher
        eventPublisher = new EventPublisher();
        // Initialize the subscriber
        notificationSubscriber = mock(NotificationSubscriber.class); // Use mock for easy testing
        // Subscribe the notificationSubscriber to "NotificationEvent"
        eventPublisher.subscribe("NotificationEvent", notificationSubscriber);

        // Clear any existing notifications in the VirtualDatabase before each test
        VirtualDatabase.notifications.clear();
    }

    @Test
    void testSubscriberReceivesEvent() {
        // Prepare event data
        Map<String, Object> eventData = Map.of("userId", 1, "message", "Your quiz has been graded!");

        // Create a notification event
        Event notificationEvent = new Event("NotificationEvent", this, eventData);

        // Publish the event
        eventPublisher.publish(notificationEvent);

        // Verify that the subscriber receives the event
        verify(notificationSubscriber, times(1)).handleEvent(notificationEvent);
    }

    @Test
    void testMultipleSubscribersReceiveEvent() {
        // Prepare multiple subscribers
        NotificationSubscriber anotherSubscriber = mock(NotificationSubscriber.class);
        eventPublisher.subscribe("NotificationEvent", anotherSubscriber);

        // Prepare event data
        Map<String, Object> eventData = Map.of("userId", 1, "message", "Your quiz has been graded!");

        // Create the event
        Event notificationEvent = new Event("NotificationEvent", this, eventData);

        // Publish the event
        eventPublisher.publish(notificationEvent);

        // Verify that both subscribers handle the event
        verify(notificationSubscriber, times(1)).handleEvent(notificationEvent);
        verify(anotherSubscriber, times(1)).handleEvent(notificationEvent);
    }

    @Test
    void testSubscriberNotifiedAndNotificationStored() {
        // Prepare event data
        Map<String, Object> eventData = Map.of("userId", 1, "message", "Your quiz has been graded!");

        // Create the event
        Event notificationEvent = new Event("NotificationEvent", this, eventData);

        // Publish the event
        eventPublisher.publish(notificationEvent);

        // Verify the subscriber receives the event
        verify(notificationSubscriber, times(1)).handleEvent(notificationEvent);

        // Now, check if the notification was stored in the VirtualDatabase for userId 1
        assertTrue(VirtualDatabase.notifications.containsKey(1), "VirtualDatabase should contain notifications for userId 1");

        // Ensure there is exactly one notification for userId 1
        assertEquals(1, VirtualDatabase.notifications.get(1).size(), "User should have exactly one notification");

        // Verify the notification's message
        Notification notification = VirtualDatabase.notifications.get(1).get(0);
        assertEquals("Your quiz has been graded!", notification.getMessage(), "Notification message should match");
    }

    @Test
    void testEventWithNoSubscribers() {
        // Create an event for which no one is subscribed
        Event unSubscribedEvent = new Event("UnknownEvent", this, Map.of("userId", 1, "message", "This shouldn't be handled"));

        // Publish the event
        eventPublisher.publish(unSubscribedEvent);

        // Since no subscriber is listening for "UnknownEvent", no verification needed here
        // The event should not be handled by any subscriber, and no notification should be stored
        assertTrue(VirtualDatabase.notifications.isEmpty(), "There should be no notifications for unknown event types");
    }
}
