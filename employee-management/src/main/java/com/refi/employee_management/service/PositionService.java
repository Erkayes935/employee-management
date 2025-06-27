package com.refi.employee_management.service;

import com.refi.employee_management.model.Position;
import com.refi.employee_management.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll()
                .stream()
                .filter(pos -> pos.getIsDelete() == 0)
                .toList();
    }
}
