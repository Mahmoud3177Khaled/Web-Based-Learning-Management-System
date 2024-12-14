package com.example.lms.lms; // Declares the package for this class

import org.springframework.boot.SpringApplication; // Imports the SpringApplication class for bootstrapping the application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Imports the SpringBootApplication annotation for auto-configuration

@SpringBootApplication // Indicates that this is a Spring Boot application
public class LmsApplication { // Declares the LmsApplication class

    public static void main(String[] args) { // Main method, entry point of the application
        SpringApplication.run(LmsApplication.class, args); // Runs the Spring Boot application with the specified class and arguments
    }

}
