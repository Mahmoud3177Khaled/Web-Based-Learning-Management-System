package com.example.lms.UserServiceTest;

import com.example.lms.entity.*;
import com.example.lms.entity.UserRequest.*;
import com.example.lms.service.UserService;
import com.example.lms.repository.VirtualDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Clear the virtual database before each test to ensure a clean state
        VirtualDatabase.students.clear();
        VirtualDatabase.instructors.clear();
        VirtualDatabase.admins.clear();
        VirtualDatabase.loginMap.clear();
        VirtualDatabase.students.put(1, new Student(1, "Jonathan Mokhles", "password", "Jonathan@gmail.com"));

    }

    @Test
    void addNewUser() {

        UserCreation userCreation = new UserCreation();
        User user = new User(2, "william Mokhles", "password", "william@gmail.com", "Admin");
        userCreation.setUser(user);

        userService.addNewUser(userCreation);

        assertTrue(VirtualDatabase.admins.containsKey(2));
        assertEquals("william Mokhles", VirtualDatabase.admins.get(2).getName());
    }

    @Test
    void addNewUser_WhenDuplicateUserId() {
        UserCreation userCreation = new UserCreation();
        User user = new User(1, "Jonathan Mokhles", "password", "Jonathan@gmail.com", "Student");
        userCreation.setUser(user);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.addNewUser(userCreation));
        assertEquals("this id id exsist please enter another id \n", exception.getMessage());
    }

    @Test
    void getUserById() {
        Object result = userService.getUserById(1);

        assertNotNull(result);
        assertTrue(result instanceof Student);
        assertEquals("Jonathan Mokhles", ((Student) result).getName());
    }

    @Test
    void getUserById_WhenUserNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.getUserById(99));
        assertEquals("User with ID 99 not found", exception.getMessage());
    }

    @Test
    void loginUser() {
        UserCreation userCreation = new UserCreation();
        User user = new User(2, "william Mokhles", "password", "william@gmail.com", "Admin");
        userCreation.setUser(user);
        VirtualDatabase.loginMap.put("william@gmail.com", user);

        User result = userService.loginUser("william@gmail.com", "password");

        assertNotNull(result);
        assertEquals("william Mokhles", result.getName());
    }

    @Test
    void loginUser_WhenInvalidUser() {

        UserCreation userCreation = new UserCreation();
        User user = new User(2, "william Mokhles", "password", "william@gmail.com", "Admin");
        userCreation.setUser(user);
        VirtualDatabase.loginMap.put("william@gmail.com", user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.loginUser("william@gmail.com", "wrongpassword"));
        assertEquals("Invalid email or password", exception.getMessage());
    }

    @Test
    void updateUser() {

        User updatedUser = new User(1, "William Mokhles", "newpassword", "william@gmail.com", "Student");

        Object result = userService.updateUser(1, updatedUser);

        assertNotNull(result);
        assertEquals("William Mokhles", ((User) result).getName());
        assertEquals("newpassword", ((User) result).getPassword());
        assertEquals("william@gmail.com", ((User) result).getEmail());
    }

    @Test
    void updateUse_WhenUserNotFound() {
        User updatedUser = new User(99, "William Mokhles", "newpassword", "william@gmail.com", "Student");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.updateUser(99, updatedUser));
        assertEquals("User with ID 99 not found", exception.getMessage());
    }

    @Test
    void isUserExisit() {

        boolean exists = userService.IsUserExisit(1);

        assertTrue(exists);
    }

    @Test
    void isUserExisit_WhenUserDoesNotExist() {
        boolean exists = userService.IsUserExisit(99);

        assertFalse(exists);
    }
}
