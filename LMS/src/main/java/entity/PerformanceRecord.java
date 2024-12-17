package entity; // Declares the package name for this class

import jakarta.persistence.Entity; // Imports the Entity annotation for JPA
import jakarta.persistence.GeneratedValue; // Imports the GeneratedValue annotation for primary key generation
import jakarta.persistence.Id; // Imports the Id annotation to mark the primary key
import jakarta.persistence.ManyToOne; // Imports the ManyToOne annotation for relationships

@Entity // Marks this class as a JPA entity, which will be mapped to a database table
public class PerformanceRecord { // Declares the PerformanceRecord class

    @Id // Marks this field as the primary key of the entity
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the strategy for generating the primary key value
    private Long id; // Declares the id field to store the primary key

   // @ManyToOne // Specifies a many-to-one relationship with the Student entity
   // private Student student; // Declares a field for the associated Student entity

   // @ManyToOne // Specifies a many-to-one relationship with the Course entity
   // private Course course; // Declares a field for the associated Course entity

    private int quizScore; // Declares a field to store the quiz score
    private int assignmentScore; // Declares a field to store the assignment score
    private int attendanceCount; // Declares a field to store the attendance count

    // Getters and Setters would be here (not shown for brevity)
}