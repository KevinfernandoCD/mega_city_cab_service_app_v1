package com.mycompany.megacity_cab_service.utils;

import interfaces.FareStrategy;

public class DiscountFare implements FareStrategy {
    @Override
    public double calculateFare(double baseFare, double distance) {
        double discountRate;

        // Using a single case with conditional operators
        switch (1) { // Dummy switch value to use default case
            default:
                discountRate = (distance <= 5) ? 2 :
                               (distance <= 15) ? 5 :
                               (distance <= 30) ? 10 : 15;
                break;
        }

        return baseFare/100*discountRate;
        
        
    }
}
