package entity;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

// import jakarta.persistence.Entity;

// @Entity
public class Course {
    private String id;
    private String tittle;
    private String description;
    private Date startDate;
    private Date endDate;
    private MediaFile[] mediaFiles;
    private Lesson[] lessons;
    private Map<Integer,Student> enrolledStudents = new HashMap<Integer,Student>();
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
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public MediaFile[] getMediaFiles() {
        return mediaFiles;
    }
    public void setMediaFiles(MediaFile[] mediaFiles) {
        this.mediaFiles = mediaFiles;
    }
    public Lesson[] getLessons() {
        return lessons;
    }
    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
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
}
