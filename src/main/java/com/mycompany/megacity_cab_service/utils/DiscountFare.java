package com.mycompany.megacity_cab_service.utils;

import interfaces.FareStrategy;

public class DiscountFare implements FareStrategy {
    @Override
    public double calculateFare(double baseFare, double distance) {
        double discountRate;

        // Using a single case with conditional operators
        switch (1) { // Dummy switch value to use default case
            default:
                discountRate = (distance <= 5) ? 0.05 :
                               (distance <= 10) ? 0.1 :
                               (distance <= 15) ? 0.15 : 0.2;
                break;
        }

        return baseFare * distance * (1 - discountRate);
    }
}
