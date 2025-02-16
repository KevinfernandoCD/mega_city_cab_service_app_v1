package com.mycompany.megacity_cab_service.utils;

import com.mycompany.megacity_cab_service.models.Admin;
import com.mycompany.megacity_cab_service.models.Driver;
import com.mycompany.megacity_cab_service.models.User;

public class UserFactory {

    public static User createUser(
            String username, String email, String password, 
            String role, String phone_No, String address, 
            boolean isAvailable) {

        if ("admin".equalsIgnoreCase(role)) {
            return new Admin(username, email, password, role);
        } else if ("driver".equalsIgnoreCase(role)) {
            return new Driver(username, email, password, phone_No, address, isAvailable);
        } else {
            throw new IllegalArgumentException("Invalid user role: " + role);
        }
    }
}
