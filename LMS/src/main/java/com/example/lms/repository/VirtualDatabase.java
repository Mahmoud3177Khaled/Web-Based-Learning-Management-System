package com.example.lms.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.QuestionBank;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.Student;

public class VirtualDatabase {

    private VirtualDatabase SingletonDB;

    public static Map<Integer,Student> students;
    public static Map<String,Course> courses;
    public static ArrayList<QuestionBank> banks;
    public static ArrayList<Quiz> quizes;

    // singleton db
    private VirtualDatabase() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.banks = new ArrayList<>();
        this.quizes = new ArrayList<>();
    }

    public VirtualDatabase getInistance() {
        if (this.SingletonDB == null) {
            this.SingletonDB = new VirtualDatabase();
        }

        return this.SingletonDB;
    }

}
