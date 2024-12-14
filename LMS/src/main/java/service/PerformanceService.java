package service;

import entity.PerformanceRecord;
import repository.PerformanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRecordRepository performanceRecordRepository;

    public List<PerformanceRecord> getStudentPerformance(Long studentId) {
        return performanceRecordRepository.findByStudentId(studentId);
    }
}
