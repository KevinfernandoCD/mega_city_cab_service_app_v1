package com.mycompany.megacity_cab_service.utils;

import interfaces.FareStrategy;

public class TaxFare implements FareStrategy {
    @Override
    public double calculateFare(double baseFare, double distance) {
        double taxRate = 0.15; // 15% tax
        return baseFare * distance * (1 + taxRate);
    }
}
