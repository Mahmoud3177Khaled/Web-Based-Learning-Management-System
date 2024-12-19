package com.example.lms.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.lms.entity.Course;

// import com.example.lms.entity.Question;
import com.example.lms.entity.QuestionBank;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.Student;
import com.example.lms.entity.Instructor;
import com.example.lms.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public class VirtualDatabase {

    private static VirtualDatabase SingletonDB;

<<<<<<< HEAD
    public static Map<Integer, Student> students;
    public static Map<Integer, Instructor> instructors;
    public static Map<Integer, Admin> admins;
    public static Map<String, Course> courses;
    public static ArrayList<QuestionBank> banks;
    public static ArrayList<Quiz> quizes;
=======
    public static Map<Integer,User> users;
    public static Map<Integer,Student> students;
    public static Map<String,Course> courses;
    // public static ArrayList<QuestionBank> banks;
    // public static ArrayList<Quiz> quizes;
>>>>>>> 484b0d464116c1fe0f17a651064689485ada9f94

    // singleton db
    private VirtualDatabase() {
        VirtualDatabase.users = new HashMap<>();
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
