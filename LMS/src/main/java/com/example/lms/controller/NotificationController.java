package com.example.lms.controller;

import com.example.lms.entity.Notification;
import com.example.lms.service.NotificationService;
import com.example.lms.event.EnrollmentEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @PostMapping("/{userId}/custom")
    public ResponseEntity<String> addCustomNotification(@PathVariable int userId, @RequestParam String message) {
        notificationService.addCustomNotification(userId, message);
        return ResponseEntity.ok("Custom notification added.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable int userId) {
        return ResponseEntity.ok(NotificationService.getNotifications(userId));
    }

    @GetMapping("/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable int userId) {
        return ResponseEntity.ok(NotificationService.getUnreadNotifications(userId));
    }

    @PutMapping("/{userId}/{notificationId}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable int userId, @PathVariable int notificationId) {
        Notification updatedNotification = notificationService.markNotificationAsRead(userId, notificationId);

        if (updatedNotification == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedNotification);
    }
}
