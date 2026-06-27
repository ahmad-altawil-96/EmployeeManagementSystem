package com.example.EmployeeManagementSystem.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name ="breaks")
@Data
@NoArgsConstructor
public class Break {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    @Column(nullable = false)
    private LocalDateTime breakStartTime;

    @Column(nullable = true)
    private LocalDateTime breakEndTime;

}
