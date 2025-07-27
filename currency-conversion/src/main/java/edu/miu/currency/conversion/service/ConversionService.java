package edu.miu.currency.conversion.service;

import edu.miu.currency.conversion.infrastructure.client.CoinbaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    @Autowired
    private CoinbaseClient coinbaseClient;

    public String convertCryptoToFiat(String from, String to, String amount) {
        return coinbaseClient.convert(from, to, amount);
    }
}