package com.refi.employee_management.controller;

import com.refi.employee_management.model.Position;
import com.refi.employee_management.service.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
@CrossOrigin(origins = "*")  // enable CORS untuk FE
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getAll() {
        return positionService.getAllPositions();
    }
}
