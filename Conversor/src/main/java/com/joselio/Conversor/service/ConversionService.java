package com.joselio.Conversor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joselio.Conversor.exception.ConversionRateNotFoundException;
import com.joselio.Conversor.exception.NegativeAmountNotAllowedException;
import com.joselio.Conversor.exception.UnsupportedUnitException;

@Service
public class ConversionService {
    private static final List<String> MOEDAS_SUPORTADAS = List.of("USD", "BRL", "EUR", "JPY", "GBP");
    private static final double USD_TO_BRL = 5.0;
    private static final double EUR_TO_BRL = 6.0;
    private static final double JPY_TO_BRL = 0.05;
    private static final double GBP_TO_BRL = 7.0;
    private static final double KM_TO_MI = 0.621371;
    private static final double MAX_VALUE = 1000000.0;

    public double convertCurrency(String from, String to, double amount) {
        if (amount > MAX_VALUE) {
             throw new NegativeAmountNotAllowedException("Valor excede o limite máximo de: " + MAX_VALUE);
        }

        if (amount < 0) {
            throw new NegativeAmountNotAllowedException("Valores negativos não são permitidos.");
        }

        String fromUpper = from.toUpperCase();
        String toUpper = to.toUpperCase();

        if (!MOEDAS_SUPORTADAS.contains(fromUpper) || !MOEDAS_SUPORTADAS.contains(toUpper)) {
            throw new UnsupportedUnitException("Moeda não suportada. Suportadas: " + MOEDAS_SUPORTADAS.toString());
        }

        if (fromUpper.equals(toUpper)) {
            return amount;
        }

        double amountInBRL = toBRL(fromUpper, amount);
        return fromBRL(toUpper, amountInBRL);
    }

    private double toBRL(String from, double amount) {
        return switch (from) {
            case "USD" -> amount * USD_TO_BRL;
            case "EUR" -> amount * EUR_TO_BRL;
            case "JPY" -> amount * JPY_TO_BRL;
            case "GBP" -> amount * GBP_TO_BRL;
            case "BRL" -> amount;
            default -> throw new ConversionRateNotFoundException("Taxa de conversão para BRL ausente para a moeda: " + from);
        };
    }

    private double fromBRL(String to, double amountInBRL) {
        return switch (to) {
            case "USD" -> amountInBRL / USD_TO_BRL;
            case "EUR" -> amountInBRL / EUR_TO_BRL;
            case "JPY" -> amountInBRL / JPY_TO_BRL;
            case "GBP" -> amountInBRL / GBP_TO_BRL;
            case "BRL" -> amountInBRL;
            default -> throw new ConversionRateNotFoundException("Taxa de conversão de BRL ausente para a moeda: " + to);
        };
    }

    public double convertUnit(String type, String from, String to, double value) {

        if (value < 0 && !type.equalsIgnoreCase("temperature")) {
            throw new NegativeAmountNotAllowedException("Valores negativos não são permitidos para esta unidade.");
        }

        String fromUpper = from.toUpperCase();
        String toUpper = to.toUpperCase();
        String typeLower = type.toLowerCase();

        if (fromUpper.equals(toUpper)) {
            return value;
        }

        return switch (typeLower) {
            case "distance" -> convertDistance(fromUpper, toUpper, value);
            case "temperature" -> convertTemperature(fromUpper, toUpper, value);
            default -> throw new UnsupportedUnitException("Tipo de conversão não suportado: " + type);
        };
    }

    private double convertDistance(String from, String to, double value) {
        if (from.equals("KM") && to.equals("MI")) {
            return value * KM_TO_MI;
        } else if (from.equals("MI") && to.equals("KM")) {
            return value / KM_TO_MI;
        } else {
            throw new UnsupportedUnitException("Unidades de distância não suportadas: " + from + " para " + to);
        }
    }

    private double convertTemperature(String from, String to, double value) {
        if (from.equals("C") && to.equals("F")) {
            return (value * 9/5) + 32;
        } else if (from.equals("F") && to.equals("C")) {
            return (value - 32) * 5/9;
        } else {
            throw new UnsupportedUnitException("Unidades de temperatura não suportadas: " + from + " para " + to);
        }
    }
}
