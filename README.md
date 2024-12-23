# Web-Based Learning Management System (LMS) - Back-End API

## Overview
This repository contains the back-end API for a Web-Based Learning Management System (LMS) built using Java and Spring Boot. The API facilitates the management of courses, assignments, quizzes, attendance, notifications, and user interactions in an educational environment.

## Features
- **User Authentication**: Secure login and registration for students and instructors.
- **Course Management**: Create, update, delete, and retrieve courses.
- **Assignment Management**: Create, submit, and grade assignments.
- **Quiz Management**: Create, submit, and grade quizzes.
- **Attendance Management**: Generate OTP codes for lessons and manage attendance.
- **Notification System**: Send and retrieve notifications for users.

## Technologies Used
- **Java**: The primary programming language for back-end development.
- **Spring Boot**: For building the RESTful API.
- **Maven**: For dependency management and project build.
- **JUnit**: For unit testing.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven
- An IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Mahmoud3177Khaled/Web-Based-Learning-Management-System.git
   cd Web-Based-Learning-Management-System
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### API Endpoints
- **User Management**:
  - `POST /register/submit` - Register a new user
  - `POST /login` - User login
  - `GET /user/{userId}` - Get user details
  - `PUT /user/{userId}` - Update user details

- **Course Management**:
  - `POST /courseManagement/addNewCourse` - Add a new course
  - `POST /courseManagement/removeCourse` - Remove a course
  - `POST /courseManagement/getCourse` - Get course details
  - `POST /courseManagement/getAllCourses` - Get all courses

- **Assignment Management**:
  - `POST /assignment/create` - Create a new assignment
  - `POST /assignment/submit` - Submit an assignment
  - `GET /assignment/grade` - Grade an assignment submission

- **Quiz Management**:
  - `POST /quiz/create` - Create a new quiz
  - `POST /quiz/submit` - Submit quiz answers
  - `GET /quiz/grade` - Grade a quiz submission

- **Attendance Management**:
  - `POST /attendance/generateLessonOTP` - Generate OTP for a lesson
  - `POST /attendance/lessonAttends` - Mark attendance for a lesson

- **Notification Management**:
  - `POST /notifications/{senderId}/custom` - Add a custom notification
  - `GET /notifications/{userId}` - Get all notifications
  - `GET /notifications/{userId}/unread` - Get unread notifications
  - `PUT /notifications/{userId}/{notificationId}/read` - Mark notification as read

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.
