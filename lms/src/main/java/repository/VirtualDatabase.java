package repository;

import java.util.HashMap;
import java.util.Map;

import entity.Course;
import entity.Student;

public class VirtualDatabase {
    public static Map<Integer,Student> students = new HashMap<>();
    public static Map<String,Course> courses = new HashMap<>();

}
