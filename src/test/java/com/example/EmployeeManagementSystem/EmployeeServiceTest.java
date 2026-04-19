package com.example.EmployeeManagementSystem;

import com.example.EmployeeManagementSystem.Service.EmployeeService;
import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import com.example.EmployeeManagementSystem.exception.BusinessException;
import com.example.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Location;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployee_WhenEmailExists_ThrowsBusinessException() {
        // ARRANGE
        EmployeeRequest request = new EmployeeRequest();
        request.setEmail("ahmad@test.com");

        when(employeeRepository.existsByEmail("ahmad@test.com"))
                .thenReturn(true);

        // ACT & ASSERT
        assertThatThrownBy(() -> employeeService.createEmployee(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Email already exists");
    }

    @Test
    void createEmployee_WhenDepartmentNotFound_ThrowsResourceNotFoundException() {
        // ARRANGE
        EmployeeRequest request = new EmployeeRequest();
        request.setEmail("ahmad@test.com");
        request.setDepartmentId(99L);

        when(employeeRepository.existsByEmail("ahmad@test.com"))
                .thenReturn(false);
        when(departmentRepository.findById(99L))
                .thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThatThrownBy(() -> employeeService.createEmployee(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Department with id 99 not found");
    }

    @Test
    void createEmployee_WhenlocationNotFound_ThrowsResourceNotFoundException() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmail("ahmad@test.com");
        request.setDepartmentId(99L);
        request.setLocationId(33L);

        when(employeeRepository.existsByEmail("ahmad@test.com"))
                .thenReturn(false);
        Department department = new Department();
        department.setId(99L);
        department.setName("IT");
        when(departmentRepository.findById(99L)).thenReturn(Optional.of(department));
        when(locationRepository.findById(33L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.createEmployee(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Location with id 33 not found");
    }

    @Test
    void createEmployee_WhenAllDataValid_ReturnsEmployeeResponse() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmail("ahmad@test.com");
        request.setDepartmentId(99L);
        request.setLocationId(33L);
        request.setPassword("password");
        Department department = new Department();
        department.setId(99L);
        department.setName("IT");
        Location location = new Location();
        location.setId(33L);
        location.setName("Essen");
        location.setAddress("Eckenbergstr");

        when(locationRepository.findById(33L)).thenReturn(Optional.of(location));
        when(departmentRepository.findById(99L)).thenReturn(Optional.of(department));
        when(employeeRepository.existsByEmail("ahmad@test.com"))
                .thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encoded");

        EmployeeResponse response = employeeService.createEmployee(request);


        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo("ahmad@test.com");
        assertThat(response.getDepartmentName()).isEqualTo("IT");
        assertThat(response.getLocationName()).isEqualTo("Essen");
    }
}
