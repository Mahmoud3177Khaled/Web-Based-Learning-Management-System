package com.example.lms.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.lms.entity.Notification;
import com.example.lms.event.Event;
import com.example.lms.repository.VirtualDatabase;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NotificationSubscriberTest {

    private NotificationSubscriber notificationSubscriber;

    @BeforeEach
    void setUp() {
        notificationSubscriber = new NotificationSubscriber();
    }

    @Test
    void testHandleNotificationEvent() {
        // Prepare event data
        Map<String, Object> eventData = Map.of("userId", 1, "message", "Your quiz has been graded!");

        // Create a notification event
        Event notificationEvent = new Event("NotificationEvent", this, eventData);

        // Handle the event
        notificationSubscriber.handleEvent(notificationEvent);

        // Check if the notification is added to the VirtualDatabase
        assertTrue(VirtualDatabase.notifications.containsKey(1), "Notifications should contain key 1 (userId)");
        assertEquals(1, VirtualDatabase.notifications.get(1).size(), "User should have one notification");

        Notification notification = VirtualDatabase.notifications.get(1).get(0);
        assertEquals("Your quiz has been graded!", notification.getMessage(), "The notification message should match");
    }

    @Test
    void testEmptyEventData() {
        // Create an event with no useful data
        Event emptyEvent = new Event("NotificationEvent", this, Map.of());

        // Handle the event (no actual notification data)
        notificationSubscriber.handleEvent(emptyEvent);

        // Ensure no notifications were added since the event was empty
        assertTrue(VirtualDatabase.notifications.isEmpty(), "No notifications should be added for empty event data");
    }
}
