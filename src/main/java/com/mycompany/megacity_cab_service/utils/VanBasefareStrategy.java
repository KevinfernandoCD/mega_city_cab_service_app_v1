/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.megacity_cab_service.utils;

import interfaces.BasefareStrategy;

/**
 *
 * @author nuclei
 */
public class VanBasefareStrategy implements BasefareStrategy {
    
     private static final double BASE_RATE_PER_KM = 15.0;



    @Override
    public double calculateBasefare(double distance) {
                return distance * BASE_RATE_PER_KM;

    }
    
}
