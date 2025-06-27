package com.refi.employee_management.repository;

import com.refi.employee_management.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {}
