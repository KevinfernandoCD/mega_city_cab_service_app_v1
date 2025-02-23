package com.mycompany.megacity_cab_service.utils;

import interfaces.FareStrategy;

public class DiscountFare implements FareStrategy {
    @Override
    public double calculateFare(double baseFare, double distance) {
        double discountRate = 0.1; // 10% discount
        return baseFare * distance * (1 - discountRate);
    }
}
