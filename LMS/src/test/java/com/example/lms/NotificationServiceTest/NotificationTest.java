package com.example.lms.NotificationServiceTest;

import com.example.lms.entity.Notification;
import com.example.lms.entity.Admin;
import com.example.lms.entity.Instructor;
import com.example.lms.service.NotificationService;
import com.example.lms.service.UserService;
import com.example.lms.repository.VirtualDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationTest {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Initialize NotificationService
        VirtualDatabase.notifications.clear();
        notificationService = new NotificationService();
        userService = new UserService();

        notificationService.setUserService(userService);

        VirtualDatabase.admins.put(1, new Admin(1, "Jonathan Mokhles", "password", "Jonathan@gmail.com"));
        VirtualDatabase.instructors.put(22, new Instructor(22, "Jonathan Mokhles", "password", "Jonathan@gmail.com"));

        notificationService.addCustomNotification(1, "messege");
    }

    @Test
    void addCustomNotification() {

        notificationService.addCustomNotification(1, "New Notification");

        List<Notification> notifications = VirtualDatabase.notifications.get(1);
        assertNotNull(notifications);
        assertEquals(2, notifications.size());
        assertEquals("New Notification", notifications.get(1).getMessage());
        assertFalse(notifications.get(1).isRead());
    }

    @Test
    void addCustomNotification_UserDoesNotExist() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notificationService.addCustomNotification(2, "New Notification");;
        });

        assertEquals("User with ID 2 does not exist.", exception.getMessage());
    }

    @Test
    void markNotificationAsRead() {

        Notification updatedNotification = notificationService.markNotificationAsRead(1, 1);

        assertNotNull(updatedNotification);
        assertTrue(updatedNotification.isRead());
    }

    @Test
    void markNotificationAsRead_NotificationDoesNotExist() {

        Notification updatedNotification = notificationService.markNotificationAsRead(1, 5);

        assertNull(updatedNotification);
    }

    @Test
    void getUnreadNotifications() {

        notificationService.addCustomNotification(22, "1 Notification");
        notificationService.addCustomNotification(22, "2 Notification");
        notificationService.addCustomNotification(22, "3 Notification");

        List<Notification> unreadNotifications = notificationService.getUnreadNotifications(22);

        assertNotNull(unreadNotifications);
        assertEquals(3, unreadNotifications.size());
        assertFalse(unreadNotifications.get(0).isRead());
    }

    @Test
    void getUnreadNotifications_AllNotificationsAreRead() {
        notificationService.markNotificationAsRead(1, 1);
        List<Notification> unreadNotifications = notificationService.getUnreadNotifications(1);

        assertNotNull(unreadNotifications);
        assertTrue(unreadNotifications.isEmpty());
    }

    void getNotifications() {
        notificationService.addCustomNotification(22, "1 Notification");
        notificationService.addCustomNotification(22, "2 Notification");
        notificationService.addCustomNotification(22, "3 Notification");
        notificationService.markNotificationAsRead(22, 2);


        List<Notification> notifications = notificationService.getNotifications(22);

        assertNotNull(notifications);
        assertEquals(3, notifications.size());
    }

    @Test
void getNotifications_WhenUserHasNoNotifications() {

    List<Notification> notifications = notificationService.getNotifications(22);

    assertNotNull(notifications);
    assertTrue(notifications.isEmpty());
}

}