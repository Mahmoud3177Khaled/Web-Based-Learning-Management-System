package com.example.lms.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lms.entity.Admin;
import com.example.lms.entity.Course;
import com.example.lms.entity.Instructor;
import com.example.lms.entity.QuestionBank;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.Student;
import com.example.lms.entity.User;
import com.example.lms.entity.Notification;
@Service
public class VirtualDatabase {

    private static VirtualDatabase SingletonDB;

    public static Map<Integer, Admin> admins;
    public static Map<Integer, Student> students;
    public static Map<Integer, Instructor> instructors;
    public static Map<String, User> loginMap;
    public static Map<String, Course> courses;
    public static ArrayList<QuestionBank> banks;
    public static ArrayList<Quiz> quizes;
    public static HashMap<Integer, List<Notification>> notifications;

    // singleton db
    private VirtualDatabase() {
        VirtualDatabase.notifications = new HashMap<>();
        VirtualDatabase.loginMap = new HashMap<>();
        VirtualDatabase.students = new HashMap<>();
        VirtualDatabase.admins = new HashMap<>();
        VirtualDatabase.instructors = new HashMap<>();
        VirtualDatabase.courses = new HashMap<>();
        // VirtualDatabase.banks = new ArrayList<>();
        // VirtualDatabase.quizes = new ArrayList<>();
    }

    public VirtualDatabase getInistance() {
        if (VirtualDatabase.SingletonDB == null) {
            VirtualDatabase.SingletonDB = new VirtualDatabase();
        }

        return VirtualDatabase.SingletonDB;
    }





}
