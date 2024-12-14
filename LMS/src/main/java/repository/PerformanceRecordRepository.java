package repository;

import entity.PerformanceRecord;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRecordRepository extends CrudRepository<PerformanceRecord, Long> {
    List<PerformanceRecord> findByStudentId(Long studentId);
}