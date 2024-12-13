package com.Vance_Assignment.Vance_Assignment.controller;

import com.Vance_Assignment.Vance_Assignment.dto.Request;
import com.Vance_Assignment.Vance_Assignment.model.ForexData;
import com.Vance_Assignment.Vance_Assignment.service.ForexDataService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ForexDataController {

    @Autowired
    private ForexDataService service;

    @PostMapping("/forex-data")
    @Operation(summary = "Get Forex Data", description = "Retrieve historical forex data.")
    public List<ForexData> getForexData(@RequestBody Request data) {
        return service.getHistoricalData(data.from(), data.to(), data.period());
    }
}

