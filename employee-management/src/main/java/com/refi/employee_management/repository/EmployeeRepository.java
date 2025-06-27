package com.refi.employee_management.repository;

import com.refi.employee_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByIdNumber(String idNumber);

    boolean existsByIdNumber(String idNumber);
}
