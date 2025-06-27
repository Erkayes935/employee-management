package com.refi.employee_management.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "T1_POSITION")
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IS_DELETE", nullable = false)
    private Integer isDelete = 0;
}
