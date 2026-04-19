package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import com.example.EmployeeManagementSystem.exception.BusinessException;
import com.example.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.model.EmployeeStatus;
import com.example.EmployeeManagementSystem.model.Location;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        String email = request.getEmail().toLowerCase();
        if (employeeRepository.existsByEmail(email)) {
            throw new BusinessException("Email already exists");
        }
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department with id " + request.getDepartmentId() + " not found"));
        Location location = null;
        if (request.getLocationId() != null) {
            location = locationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Location with id " + request.getLocationId() + " not found"
                    ));
        }
        String rawPin = generatePin();

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail().toLowerCase());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setPin(passwordEncoder.encode(rawPin));
        employee.setPosition(request.getPosition());
        employee.setRole(request.getRole());
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setDepartment(department);
        employee.setLocation(location);

        employeeRepository.save(employee);
        return new EmployeeResponse(employee);
    }

    private String generatePin() {
        int pin = (int)(Math.random() * 9000) + 1000;
        return String.valueOf(pin);
    }
}
