package com.joselio.Conversor.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class UnitConversionRequest {
    @NotBlank(message = "Unidade de Origem é obrigatória.")
    private String from;

    @NotBlank(message = "Unidade de Destino é obrigatória.")
    private String to;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    private double value;

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
