package com.example.lms.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.lms.entity.Course;
import com.example.lms.entity.Question;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.Student;

public class VirtualDatabase {
    public static Map<Integer,Student> students = new HashMap<>();
    public static Map<String,Course> courses = new HashMap<>();
    public static ArrayList<Question> banks = new ArrayList<>();
    public static ArrayList<Quiz> quizes = new ArrayList<>();

}
