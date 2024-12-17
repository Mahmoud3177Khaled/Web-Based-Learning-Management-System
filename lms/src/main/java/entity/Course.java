package entity;

import java.util.Date;
import jakarta.persistence.Entity;

@Entity
public class Course {
    private String tittle;
    private String description;
    private Date startDate;
    private Date endDate;
    private MediaFile[] mediaFiles;
    private Lesson[] lessons;

}
