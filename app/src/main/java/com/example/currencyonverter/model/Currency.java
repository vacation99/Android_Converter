package com.example.currencyonverter.model;

public class Currency {
    private String name;
    private float rate;

    public Currency(String name, float rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public float getRate() {
        return rate;
    }
}
