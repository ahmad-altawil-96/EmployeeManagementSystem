package com.example.EmployeeManagementSystem.dto;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.model.EmployeeStatus;
import com.example.EmployeeManagementSystem.model.Role;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private EmployeeStatus status;
    private String departmentName;
    private String locationName;
    private String position;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.position = employee.getPosition();
        this.role = employee.getRole();
        this.status = employee.getStatus();
        this.departmentName = employee.getDepartment().getName();
        this.locationName = employee.getLocation() != null
                ? employee.getLocation().getName()
                : null;
    }
}
