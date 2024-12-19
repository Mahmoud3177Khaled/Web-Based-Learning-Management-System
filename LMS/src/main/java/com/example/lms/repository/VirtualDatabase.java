package com.example.lms.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.lms.entity.Course;
// import com.example.lms.entity.Question;
import com.example.lms.entity.QuestionBank;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.Student;
import org.springframework.stereotype.Service;


@Service
public class VirtualDatabase {

    private static VirtualDatabase SingletonDB;

    public static Map<Integer,Student> students;
    public static Map<String,Course> courses;
    public static ArrayList<QuestionBank> banks;
    public static ArrayList<Quiz> quizes;

    // singleton db
    private VirtualDatabase() {
        VirtualDatabase.students = new HashMap<>();
        VirtualDatabase.courses = new HashMap<>();
        VirtualDatabase.banks = new ArrayList<>();
        VirtualDatabase.quizes = new ArrayList<>();
    }

    public VirtualDatabase getInistance() {
        if (VirtualDatabase.SingletonDB == null) {
            VirtualDatabase.SingletonDB = new VirtualDatabase();
        }

        return VirtualDatabase.SingletonDB;
    }

}
