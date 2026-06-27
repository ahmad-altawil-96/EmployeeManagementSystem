package com.example.EmployeeManagementSystem.dto;

import com.example.EmployeeManagementSystem.model.Employee;
import lombok.Data;

@Data
public class CreateEmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String pin;
    private String departmentName;
    private String locationName;

    public CreateEmployeeResponse(Employee employee, String rawPin) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.pin = rawPin;
        this.departmentName = employee.getDepartment().getName();
        this.locationName = employee.getLocation().getName();
    }
}

