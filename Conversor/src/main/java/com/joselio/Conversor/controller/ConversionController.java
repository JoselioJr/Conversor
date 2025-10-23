package com.joselio.Conversor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joselio.Conversor.model.CurrencyConversionRequest;
import com.joselio.Conversor.model.UnitConversionRequest;
import com.joselio.Conversor.service.ConversionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/convert")
public class ConversionController {
    @Autowired
    private ConversionService conversionService;

    @GetMapping("/currency")
    public ResponseEntity<Map<String, Object>> convertCurrency(
        @Valid CurrencyConversionRequest request) {
    
            double result = conversionService.convertCurrency(
                request.getFrom(), request.getTo(), request.getAmount()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("from", request.getFrom());
            response.put("to", request.getTo());
            response.put("amount", request.getAmount());
            response.put("result", result);

            return ResponseEntity.ok(response);
        }

    @GetMapping("/unit/{type}")
    public ResponseEntity<Map<String, Object>> convertUnit(
        @PathVariable String type,
        @Valid UnitConversionRequest request) {

            double result = conversionService.convertUnit(
                type, request.getFrom(), request.getTo(), request.getValue() 
            );

            Map<String, Object> response = new HashMap<>();
            response.put("type", type);
            response.put("from", request.getFrom());
            response.put("to", request.getTo());
            response.put("value", request.getValue()); 
            response.put("result", result);

            return ResponseEntity.ok(response);
        }
}
