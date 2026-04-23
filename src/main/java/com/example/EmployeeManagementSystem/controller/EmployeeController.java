package com.example.EmployeeManagementSystem.controller;


import com.example.EmployeeManagementSystem.Service.EmployeeService;
import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequest request){
     return employeeService.createEmployee(request);
    }
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id,
                                           @RequestBody @Valid EmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @PatchMapping("/{id}/status")
    public EmployeeResponse inActiveEmployee(@PathVariable Long id) {
        return employeeService.inActiveEmployee(id);
    }

}
