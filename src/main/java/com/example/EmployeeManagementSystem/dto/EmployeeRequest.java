package com.example.EmployeeManagementSystem.dto;

import com.example.EmployeeManagementSystem.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Position is required")
    private String position;

    @NotNull(message = "Role is required")
    private Role role;

    @NotNull(message = "Department is required")
    private Long departmentId;

    private Long locationId;
}
