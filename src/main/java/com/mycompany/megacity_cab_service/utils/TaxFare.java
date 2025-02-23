package com.mycompany.megacity_cab_service.utils;

import interfaces.FareStrategy;

public class TaxFare implements FareStrategy {
    @Override
    public double calculateFare(double baseFare, double distance) {
        double taxRate;

        // Using a single case with conditional operators
        switch (1) { // Dummy switch value
            default:
                taxRate = (distance <= 5) ? 0.02 :    // 2% tax for 0-5 km
                          (distance <= 10) ? 0.05 :   // 5% tax for 6-10 km
                          (distance <= 15) ? 0.08 :   // 8% tax for 11-15 km
                          0.12;                       // 12% tax for 16+ km
                break;
        }

        return baseFare * distance * (1 + taxRate);
    }
}
