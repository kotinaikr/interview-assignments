package com.housing.javaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.javaapi.dto.MarketSummary;
import com.housing.javaapi.dto.WhatIfRequest;
import com.housing.javaapi.model.HouseRecord;
import com.housing.javaapi.util.CsvReaderUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketService {

    private final CsvReaderUtil csvReaderUtil;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public MarketService(
            CsvReaderUtil csvReaderUtil,
            RestTemplate restTemplate
    ) {
        this.csvReaderUtil = csvReaderUtil;
        this.restTemplate = restTemplate;
    }

    public MarketSummary getSummary() {

        List<HouseRecord> records = csvReaderUtil.loadData();

        double averagePrice =
                records.stream()
                        .mapToDouble(HouseRecord::getPrice)
                        .average()
                        .orElse(0);

        double averageArea =
                records.stream()
                        .mapToDouble(HouseRecord::getSquareFootage)
                        .average()
                        .orElse(0);

        double minimumPrice =
                records.stream()
                        .mapToDouble(HouseRecord::getPrice)
                        .min()
                        .orElse(0);

        double maximumPrice =
                records.stream()
                        .mapToDouble(HouseRecord::getPrice)
                        .max()
                        .orElse(0);

        return new MarketSummary(
                averagePrice,
                averageArea,
                minimumPrice,
                maximumPrice,
                records.size()
        );

    }

    public Object predict(WhatIfRequest request) {

        String url = "http://localhost:8000/predict";

        Map<String, Object> body =
                mapper.convertValue(request, HashMap.class);

        return restTemplate.postForObject(
                url,
                body,
                Object.class
        );

    }

}