package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.Driver;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing driver-related database operations.
 */
public class DriverDAO {

    private final DbConnection dbConnection;

    public DriverDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new driver into the database.
     *
     * @param driver The driver to insert.
     * @return A message indicating success or failure.
     */
    public String insertDriver(Driver driver) {
        String query = "INSERT INTO drivers (name, userId, phone_No, address, isAvailable) VALUES (?, ?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    driver.getUsername(),
                    driver.getUserId(),
                    driver.getPhoneNo(),
                    driver.getAddress(),
                    driver.isAvailable()
            );
            return "Driver has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add driver: " + e.getMessage();
        }
    }

    /**
     * Updates a driver's details in the database.
     *
     * @param driver The driver object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateDriver(Driver driver) {
        String query = "UPDATE drivers SET  phone_No = ?, address = ?, isAvailable = ? WHERE id = ?";
        System.out.println("SDADADS" +  driver.getPhoneNo()+ 
                    driver.getAddress() +
                    driver.isAvailable() + 
                    driver.getId());
        try {
            dbConnection.executeWriteQuery(
                    query,
                    driver.getPhoneNo(),
                    driver.getAddress(),
                    driver.isAvailable(),
                    driver.getId()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a driver from the database by driver ID.
     *
     * @param driverId The driver ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteDriver(String driverId) {
        String query = "DELETE FROM drivers WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, driverId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a driver by their driver ID.
     *
     * @param driverId The driver ID to search for.
     * @return A Driver object if found, otherwise null.
     */
    public Driver getDriverById(String driverId) {
        String query = "SELECT * FROM drivers WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, driverId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractDriverFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all drivers from the database.
     *
     * @return A list of Driver objects.
     */
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                drivers.add(extractDriverFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    /**
     * Retrieves all available drivers from the database.
     *
     * @return A list of available Driver objects.
     */
    public List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        String query = "SELECT * FROM drivers WHERE isAvailable = true";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                availableDrivers.add(extractDriverFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableDrivers;
    }


    /**
     * Extracts a Driver object from a ResultSet.
     *
     * @param rs The ResultSet containing driver data.
     * @return A Driver object.
     * @throws SQLException If a database access error occurs.
     */
    private Driver extractDriverFromResultSet(ResultSet rs) throws SQLException {
    return new Driver(
            rs.getInt("id"),
            rs.getInt("userId"),
            rs.getString("name"),
            rs.getString("phone_No"),
            rs.getString("address"),
            rs.getBoolean("isAvailable")
    );
}

}