// package controller; // Declares the package name for this class

// import entity.PerformanceRecord; // Imports the PerformanceRecord entity class
// import service.PerformanceService; // Imports the PerformanceService class
// import org.springframework.beans.factory.annotation.Autowired; // Imports the Autowired annotation for dependency injection
// import org.springframework.http.ResponseEntity; // Imports the ResponseEntity class for HTTP responses
// import org.springframework.web.bind.annotation.*; // Imports Spring Web annotations for RESTful web services

// import java.util.List; // Imports the List interface from Java Collections

// @RestController // Marks this class as a REST controller, allowing it to handle HTTP requests
// @RequestMapping("/api/performance") // Maps HTTP requests to /api/performance to this controller
// public class PerformanceController { // Declares the PerformanceController class

//     @Autowired // Automatically injects the PerformanceService bean into this field
//     private PerformanceService performanceService; // Declares a private field for PerformanceService

//     @GetMapping("/{studentId}") // Maps HTTP GET requests to this method with a path variable
//     public ResponseEntity<List<PerformanceRecord>> getPerformance(@PathVariable Long studentId) { // Method to get performance records for a specific student
//         return ResponseEntity.ok(performanceService.getStudentPerformance(studentId)); // Calls the service method and returns the performance records wrapped in a ResponseEntity
//     }
// }
