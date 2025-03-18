package com.co.itm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Component
public class ExchangeRateConfig {
    private Map<String, Double> exchangeRates;

    public ExchangeRateConfig() {
        loadRates();
    }

    private void loadRates() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/config.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            exchangeRates = objectMapper.readValue(jsonData, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exchange rates", e);
        }
    }

    public Double getExchangeRate(String currency) {
        return exchangeRates.getOrDefault(currency, 1.0);
    }
}
