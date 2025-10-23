package com.joselio.Conversor.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CurrencyConversionRequest {
    private static final String MAX_VALUE = "1000000";

    @NotBlank(message = "Moeda de Origem é obrigatória.")
    @Size(min = 3, max = 3, message = "O código da moeda deve ter 3 letras (ex: USD).")
    private String from;

    @NotBlank(message = "Moeda de Destino é obrigatória.")
    @Size(min = 3, max = 3, message = "O código da moeda deve ter 3 letras (ex: BRL).")
    private String to;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    private double amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
