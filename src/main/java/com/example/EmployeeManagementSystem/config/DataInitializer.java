package com.example.EmployeeManagementSystem.config;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.model.EmployeeStatus;
import com.example.EmployeeManagementSystem.model.Role;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Department department = departmentRepository.findByName("Management")
                .orElseGet(() -> {
                    Department dept = new Department();
                    dept.setName("Management");
                    return departmentRepository.save(dept);
                });

        if (!employeeRepository.existsByEmail("admin@company.com")) {
            Employee admin = new Employee();
            admin.setFirstName("Admin");
            admin.setLastName("System");
            admin.setEmail("admin@company.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPin(passwordEncoder.encode("0000"));
            admin.setPosition("System Administrator");
            admin.setRole(Role.ADMIN);
            admin.setStatus(EmployeeStatus.ACTIVE);
            admin.setDepartment(department);
            employeeRepository.save(admin);
            System.out.println("✅ Admin created: admin@company.com / admin123");
        }
    }
}
