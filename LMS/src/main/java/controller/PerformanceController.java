package controller;

import entity.PerformanceRecord;
import service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<PerformanceRecord>> getPerformance(@PathVariable Long studentId) {
        return ResponseEntity.ok(performanceService.getStudentPerformance(studentId));
    }
}
