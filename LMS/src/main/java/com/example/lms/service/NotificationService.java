package com.example.lms.service;

import com.example.lms.entity.Notification;
import com.example.lms.repository.VirtualDatabase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    
    public static List<Notification> getNotifications(int userId) {
        return VirtualDatabase.notifications.getOrDefault(userId, new ArrayList<>());
    }

    public static List<Notification> getUnreadNotifications(int userId) {
        List<Notification> unreadNotifications = new ArrayList<>();
        for (Notification notification : VirtualDatabase.notifications.getOrDefault(userId, new ArrayList<>())) {
            if (!notification.isRead()) {
                unreadNotifications.add(notification);
            }
        }
        return unreadNotifications;
    }

    public Notification markNotificationAsRead(int userId, int notificationId) {
        Notification notificationToUpdate = null;

        for (Notification notification : getNotifications(userId)) {
            if (notification.getId() == notificationId) {
                notification.setRead(true);
                notificationToUpdate = notification;
                break;
            }
        }
        return notificationToUpdate;
    }

    public void addCustomNotification(int userId, String message) {
        Notification notification = new Notification(
                getNotifications(userId).size() + 1,
                message,
                false);

                VirtualDatabase.notifications.computeIfAbsent(userId, k -> new ArrayList<>()).add(notification);
    }
}