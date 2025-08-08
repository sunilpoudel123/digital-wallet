package edu.miu.currency.conversion.controller;

import edu.miu.currency.conversion.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping
    public ResponseEntity<String> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String amount) {
        String response = conversionService.convertCryptoToFiat(from, to, amount);
        return ResponseEntity.ok(response);
    }
}
