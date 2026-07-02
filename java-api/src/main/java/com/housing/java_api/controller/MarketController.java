package com.housing.java_api.controller;

import com.housing.javaapi.dto.MarketSummary;
import com.housing.javaapi.dto.WhatIfRequest;
import com.housing.javaapi.service.MarketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market")
@CrossOrigin("*")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/summary")
    public MarketSummary summary() {
        return marketService.getSummary();
    }

    @PostMapping("/what-if")
    public Object whatIf(
            @RequestBody WhatIfRequest request
    ) {
        return marketService.predict(request);
    }

}