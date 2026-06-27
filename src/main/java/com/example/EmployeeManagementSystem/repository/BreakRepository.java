package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Break;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreakRepository extends JpaRepository<Break, Long>{
    Optional<Break> findByAttendanceIdAndBreakEndTimeIsNull(Long attendanceId);
}
