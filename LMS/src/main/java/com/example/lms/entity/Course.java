package com.example.lms.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import jakarta.persistence.Entity;

// @Entity
public class Course{
    private String id;
    private String tittle;
    private String description;
    private String startDate;
    private String endDate;
    private int instructorId;
    private String state;

    
    private ArrayList<Quiz> quizes;
    private ArrayList<QuizSubmission> quizSubmissions;
    private QuestionBank bank;

    private List<MediaFile> mediaFiles = new ArrayList<>();
    private Map<Integer,Lesson> lessons = new HashMap<>();
    private Map<Integer,Student> enrolledStudents = new HashMap<>();

    public Course(String id) {
        this.id = id;
        this.bank = new QuestionBank();
        this.quizes = new ArrayList<>();
        this.quizSubmissions = new ArrayList<>();
    }
    
    public Course(String id, String tittle, String description,int instructorId , String startDate, String endDate){
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = "open";
        this.bank = new QuestionBank();
        this.quizes = new ArrayList<>();
        this.quizSubmissions = new ArrayList<>();
    } 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }
    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }
    public Map<Integer,Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Map<Integer,Lesson> lessons) {
        this.lessons = lessons;
    }
    public Lesson getLesson(int lessonNumber){
        return lessons.get(lessonNumber);
    }
    public void addLesson(Lesson lesson) {
        this.lessons.put(lesson.getLessonNumber(), lesson);
    }
    public void removeLesson(int lessonNumber) {
        this.lessons.remove(lessonNumber);
    }
    public Map<Integer,Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    public void setEnrolledStudents( Map<Integer,Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
    public boolean addStudent(Student student){
        this.enrolledStudents.put(student.getId(), student);
        return true;
    }
    public boolean addMediaFile(MediaFile mediaFile){
        this.mediaFiles.add(mediaFile);
        return true;
    }


    public void setQuizes(ArrayList<Quiz> quizes) {
        this.quizes = quizes;
    }

    public ArrayList<Quiz> getQuizes() {
        return this.quizes;
    }

    public void setBank(QuestionBank bank) {
        this.bank = bank;
    }

    public QuestionBank getBank() {
        return bank;
    }

    public void addQuiz(Quiz quiz) {
        this.quizes.add(quiz);
    }
    
    public void addQuestionToBank(Question question) {
        this.bank.addQuastion(question);
    }
    
    public void setQuizSubmissions(ArrayList<QuizSubmission> quizSubmissions) {
        this.quizSubmissions = quizSubmissions;
    }
    
    public ArrayList<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    public void addSubmission(QuizSubmission submission) {
        this.quizSubmissions.add(submission);
    }
    
    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public boolean isAvailable() {
        return this.state.equals("open");
    }
}
