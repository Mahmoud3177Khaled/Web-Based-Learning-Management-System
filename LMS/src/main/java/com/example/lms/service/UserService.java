package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Instructor;
import com.example.lms.entity.User;
import com.example.lms.entity.UserRequest.UserCreation;
import com.example.lms.entity.UserRequest.UpdatedUser;
import com.example.lms.entity.Student;
import com.example.lms.entity.Admin;
import com.example.lms.repository.VirtualDatabase;

@Service
public class UserService {
    @Autowired
    VirtualDatabase virtualDatabase;

    public void addNewUser(UserCreation user) {

        if (IsUserExisit(user.getUser().getId())) {
            throw new IllegalArgumentException("this id exsist please enter another id \n");
        }
        if (VirtualDatabase.loginMap.containsKey(user.getUser().getEmail())) {
            throw new IllegalArgumentException("this email is used please enter another email");
        }

        switch (user.getUser().getUserType()) {
            case "Student":
                VirtualDatabase.students.put(
                        user.getUser().getId(),
                        new Student(
                                user.getUser().getId(),
                                user.getUser().getName(),
                                user.getUser().getPassword(),
                                user.getUser().getEmail()));
                InsertLogin(user);
                break;
            case "Instructor":
                VirtualDatabase.instructors.put(
                        user.getUser().getId(),
                        new Instructor(
                                user.getUser().getId(),
                                user.getUser().getName(),
                                user.getUser().getPassword(),
                                user.getUser().getEmail()));
                InsertLogin(user);
                break;
            case "Admin":
                VirtualDatabase.admins.put(
                        user.getUser().getId(),
                        new Admin(
                                user.getUser().getId(),
                                user.getUser().getName(),
                                user.getUser().getPassword(),
                                user.getUser().getEmail()));
                InsertLogin(user);
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + user.getUser().getUserType());

        }

    }

    public Object getUserById(int id) {
        if (VirtualDatabase.students.containsKey(id)) {
            return VirtualDatabase.students.get(id);
        } else if (VirtualDatabase.instructors.containsKey(id)) {
            return VirtualDatabase.instructors.get(id);
        } else if (VirtualDatabase.admins.containsKey(id)) {
            return VirtualDatabase.admins.get(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    private void InsertLogin(UserCreation user) {
        VirtualDatabase.loginMap.put(
                user.getUser().getEmail(),
                new Student(
                        user.getUser().getId(),
                        user.getUser().getName(),
                        user.getUser().getPassword(),
                        user.getUser().getEmail()));
    }

    public boolean IsUserExisit(int id) {

        if (VirtualDatabase.students.containsKey(id))
            return true;
        else if (VirtualDatabase.instructors.containsKey(id))
            return true;
        else if (VirtualDatabase.admins.containsKey(id))
            return true;
        else
            return false;
    }

    public User loginUser(String email, String password) {
        User user = VirtualDatabase.loginMap.get(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }

    public Object updateUser(int id, UpdatedUser updatedUser) {
        if (VirtualDatabase.students.containsKey(id)) {
            Student existingStudent = VirtualDatabase.students.get(id);
            updateUserDetails(existingStudent, updatedUser);
            return existingStudent;
        }

        else if (VirtualDatabase.instructors.containsKey(id)) {
            Instructor existingInstructor = VirtualDatabase.instructors.get(id);
            updateUserDetails(existingInstructor, updatedUser);
            return existingInstructor;
        }

        else if (VirtualDatabase.admins.containsKey(id)) {
            Admin existingAdmin = VirtualDatabase.admins.get(id);
            updateUserDetails(existingAdmin, updatedUser);
            return existingAdmin;
        }

        throw new IllegalArgumentException("User with ID " + id + " not found");
    }

    private void updateUserDetails(User existingUser, UpdatedUser updatedUser) {
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
    }
}
