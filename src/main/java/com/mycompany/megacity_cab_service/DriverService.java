package com.mycompany.megacity_cab_service;

import com.mycompany.megacity_cab_service.dao.DriverDAO;
import com.mycompany.megacity_cab_service.dao.UserDAO;
import com.mycompany.megacity_cab_service.models.Driver;
import com.mycompany.megacity_cab_service.models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing driver-related operations.
 * This class acts as a layer between the DAO and the application logic.
 */
public class DriverService {

    private final DriverDAO driverDAO;
    private final UserDAO userDAO;

    public DriverService() throws SQLException, ClassNotFoundException {
        this.driverDAO = new DriverDAO();
        this.userDAO = new UserDAO();
    }

    /**
     * Adds a new driver.
     *
     * @param driver The driver object to add.
     * @return A message indicating the result of the operation.
     */
    public String addDriver(Driver driver) {
    try {
        // Check if a user with the given email already exists
        User existingUser = userDAO.getUserByEmail(driver.getEmail());
        if (existingUser != null) {
            return "A user with this email already exists.";
        }

        // Insert user first and retrieve the generated user ID
        userDAO.insertUser((User) driver);
        User newUser = userDAO.getUserByEmail(driver.getEmail());
        

        // Set the user ID in the driver object
        driver.setUserId(newUser.getId());

        // Insert the driver
        return driverDAO.insertDriver(driver);
    } catch (Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}


    /**
     * Updates an existing driver's information.
     *
     * @param driver The driver object with updated information.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateDriver(Driver driver) {
        return driverDAO.updateDriver(driver);
    }

    /**
     * Removes a driver by their driver ID.
     *
     * @param driverId The ID of the driver to remove.
     * @return true if deletion was successful, false otherwise.
     */
    public boolean removeDriver(String driverId) {
        
        Driver driver = driverDAO.getDriverById(driverId);
        
        if(driver == null) { 
            return false; 
        }else{ 
            userDAO.deleteUser(driver.getUserId());
        }
        return driverDAO.deleteDriver(driverId);
    }

    /**
     * Retrieves a driver by their driver ID.
     *
     * @param driverId The driver ID to search for.
     * @return A Driver object if found, otherwise null.
     */
    public Driver findDriverById(String driverId) {
        return driverDAO.getDriverById(driverId);
    }

    /**
     * Retrieves a list of all drivers.
     *
     * @return A list of all Driver objects.
     */
    public List<Driver> findAllDrivers() {
        return driverDAO.getAllDrivers();
    }

    /**
     * Retrieves a list of all available drivers.
     *
     * @return A list of available Driver objects.
     */
    public List<Driver> findAvailableDrivers() {
        return driverDAO.getAvailableDrivers();
    }

    /**
     * Toggles the availability status of a driver.
     *
     * @param driverId The ID of the driver whose availability to toggle.
     * @return true if the availability was toggled successfully, false otherwise.
     */
    public boolean toggleDriverAvailability(String driverId) {
        Driver driver = findDriverById(driverId);
        if (driver != null) {
            driver.toggleAvailability();
            return updateDriver(driver); // Update the driver in the database with the toggled availability
        }
        return false; // Driver not found
    }
}