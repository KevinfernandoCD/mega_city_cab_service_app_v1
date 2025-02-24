package com.mycompany.megacity_cab_service.utils;

import interfaces.FareStrategy;

public class TaxFare implements FareStrategy {
    @Override
    public double calculateFare(double baseFare, double distance) {
        double taxRate;

        // Using a single case with conditional operators
        switch (1) { // Dummy switch value
            default:
                taxRate = (distance <= 5) ? 2 :    // 2% tax for 0-5 km
                          (distance <= 10) ? 5 :   // 5% tax for 6-10 km
                          (distance <= 15) ? 8 :   // 8% tax for 11-15 km
                          12;                       // 12% tax for 16+ km
                break;
        }

        return baseFare/100*taxRate;
    }
}
