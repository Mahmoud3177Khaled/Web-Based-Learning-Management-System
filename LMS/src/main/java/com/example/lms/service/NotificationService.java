package com.example.lms.service;

import com.example.lms.entity.Notification;

import com.example.lms.repository.VirtualDatabase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void addCustomNotification(int userId, String message) {
        if (!userService.IsUserExisit(userId)) {
        throw new IllegalArgumentException("User with ID "+ userId +" does not exist.");
        }
        Notification notification = new Notification(
                getNotifications(userId).size() + 1,
                message,
                false);

        VirtualDatabase.notifications.computeIfAbsent(userId, k -> new ArrayList<>()).add(notification);
    }

    public List<Notification> getNotifications(int userId) {
        if (!userService.IsUserExisit(userId)) {
        throw new IllegalArgumentException("User with ID "+ userId +" does not exist.");
        }

        return VirtualDatabase.notifications.getOrDefault(userId, new ArrayList<>());
    }

    public List<Notification> getUnreadNotifications(int userId) {
        if (!userService.IsUserExisit(userId)) {
        throw new IllegalArgumentException("User with ID "+ userId +" does not exist.");
        }
        List<Notification> unreadNotifications = new ArrayList<>();
        for (Notification notification : VirtualDatabase.notifications.getOrDefault(userId, new ArrayList<>())) {
            if (!notification.isRead()) {
                unreadNotifications.add(notification);
            }
        }
        return unreadNotifications;
    }

    public Notification markNotificationAsRead(int userId, int notificationId) {

        if (!userService.IsUserExisit(userId)) {
        throw new IllegalArgumentException("User with ID "+ userId +" does not exist.");
        }

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


}