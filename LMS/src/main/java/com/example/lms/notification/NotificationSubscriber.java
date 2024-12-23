package com.example.lms.notification;

import com.example.lms.event.Event;
import com.example.lms.event.EventSubscriber;
import com.example.lms.repository.VirtualDatabase;
import com.example.lms.entity.Notification;

import java.util.ArrayList;

public class NotificationSubscriber implements EventSubscriber {
    @Override
    public void handleEvent(Event event) {
        if ("NotificationEvent".equals(event.getType())) {
            int userId = (int) event.getData().get("userId");
            String message = (String) event.getData().get("message");

            VirtualDatabase.notifications
                    .computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(new Notification(userId, message));

            System.out.println("Notification added for User " + userId + ": " + message);
        }
    }
}
