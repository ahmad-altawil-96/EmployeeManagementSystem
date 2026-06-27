package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.model.Attendance;
import com.example.EmployeeManagementSystem.repository.AttendanceRepository;
import com.example.EmployeeManagementSystem.repository.BreakRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final BreakRepository breakRepository;

    public Attendance findByEmployeeIdAndDate(Long employeeId, LocalDate date) {
        Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId,date)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        return attendance;
    }
}
