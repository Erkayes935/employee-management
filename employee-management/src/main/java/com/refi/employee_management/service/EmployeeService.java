package com.refi.employee_management.service;

import com.refi.employee_management.model.Employee;
import com.refi.employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllActiveEmployees() {
        return employeeRepository.findAll()
                .stream()
                .filter(emp -> emp.getIsDelete() == 0)
                .toList();
    }

    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id)
                .filter(emp -> emp.getIsDelete() == 0);
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> update(Integer id, Employee newData) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setIdNumber(newData.getIdNumber());
            emp.setName(newData.getName());
            emp.setBirthDate(newData.getBirthDate());
            emp.setPosition(newData.getPosition());
            emp.setGender(newData.getGender());
            return employeeRepository.save(emp);
        });
    }

    public boolean softDelete(Integer id) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setIsDelete(1);
            employeeRepository.save(emp);
            return true;
        }).orElse(false);
    }

    public boolean existsByIdNumber(String idNumber) {
        return employeeRepository.existsByIdNumber(idNumber);
    }
}
