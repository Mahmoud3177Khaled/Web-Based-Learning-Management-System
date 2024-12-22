package com.example.lms.controller;

import com.example.lms.entity.Notification;
import com.example.lms.service.NotificationService;

import com.example.lms.security.AuthenticationManagement;
import com.example.lms.security.AuthorizationManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AuthenticationManagement authenticationManagement;
    @Autowired
    private AuthorizationManagement authorizationManagement;

    @PostMapping("/{userId}/custom")
    public ResponseEntity<String> addCustomNotification(@PathVariable int userId,
            @RequestParam("password") String password, @RequestParam String message) {
        if (authenticationManagement.isAuthenticate(userId, password)) {
            if (authorizationManagement.isAuthorized(userId, "Admin")) {
                notificationService.addCustomNotification(userId, message);
                return ResponseEntity.ok("Custom notification added.");
            }
            return ResponseEntity.status(404).body("You don't have an authorization.");
        }
        return ResponseEntity.status(404).body("this request need an authentication.");

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable int userId,@RequestParam("password") String password) {
        if (authenticationManagement.isAuthenticate(userId, password)) {
                return ResponseEntity.ok(NotificationService.getNotifications(userId));
            }
            return ResponseEntity.status(401).build();
            
    }

    @GetMapping("/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable int userId,@RequestParam("password") String password) {
        if (authenticationManagement.isAuthenticate(userId, password)) {
                
                return ResponseEntity.ok(NotificationService.getUnreadNotifications(userId));

            }
            return ResponseEntity.status(401).build();

        }

    @PutMapping("/{userId}/{notificationId}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable int userId,
            @RequestParam("password") String password, @PathVariable int notificationId) {
        if (authenticationManagement.isAuthenticate(userId, password)) {
                Notification updatedNotification = notificationService.markNotificationAsRead(userId, notificationId);

                if (updatedNotification == null) {
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(updatedNotification);
            }
            return ResponseEntity.status(401).build();
    }
}
