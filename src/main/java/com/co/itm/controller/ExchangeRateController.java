package com.co.itm.controller;

import com.co.itm.service.goodimpl.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/convert")
    public String convert(@RequestParam Double amount, @RequestParam String currency) {
        Double convertedAmount = exchangeRateService.convertToUSD(amount, currency);
        return "Converted Amount in USD: " + convertedAmount;
    }
}
