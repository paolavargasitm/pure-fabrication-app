package com.co.itm.service.goodimpl;

import com.co.itm.config.ExchangeRateConfig;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {
    private final ExchangeRateConfig exchangeRateConfig;

    public ExchangeRateService(ExchangeRateConfig exchangeRateConfig) {
        this.exchangeRateConfig = exchangeRateConfig;
    }

    public Double convertToUSD(Double amount, String currency) {
        Double rate = exchangeRateConfig.getExchangeRate(currency);
        return amount / rate;
    }
}
