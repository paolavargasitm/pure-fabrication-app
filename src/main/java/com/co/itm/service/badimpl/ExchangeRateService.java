package com.co.itm.service.badimpl;

import com.co.itm.config.ExchangeRateConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class ExchangeRateService {

    private Map<String, Double> exchangeRates;

    public ExchangeRateService() {

    }

    public Double convertToUSD(Double amount, String currency) {
        loadRates();
        Double rate = getExchangeRate(currency);
        return amount / rate;
    }


    private void loadRates() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/config.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            this.exchangeRates = objectMapper.readValue(jsonData, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exchange rates", e);
        }
    }

    public Double getExchangeRate(String currency) {
        return exchangeRates.getOrDefault(currency, 1.0);
    }


}
