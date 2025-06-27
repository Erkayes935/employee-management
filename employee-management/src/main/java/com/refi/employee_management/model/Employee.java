package com.refi.employee_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "T2_EMPLOYEE")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotBlank(message = "ID Number tidak boleh kosong")
    @Pattern(regexp = "\\d+", message = "ID Number harus angka")
    @Column(name = "ID_NUMBER", unique = true, nullable = false)
    private String idNumber;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POSITION_ID", nullable = false)
    private Position position;

    @Column(name = "GENDER", nullable = false)
    private Integer gender; // 1 = Laki-laki, 2 = Perempuan

    @Column(name = "IS_DELETE", nullable = false)
    private Integer isDelete = 0;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
