package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.Vehicle;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing vehicle-related database operations.
 */
public class VehicleDAO {

    private final DbConnection dbConnection;

    public VehicleDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new vehicle into the database.
     *
     * @param vehicle The vehicle to insert.
     * @return A message indicating success or failure.
     */
    public String insertVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (type, model, capacity, color, regNo, isAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    vehicle.getType(),
                    vehicle.getModel(),
                    vehicle.getCapacity(),
                    vehicle.getColor(),
                    vehicle.getRegistrationNumber(),
                    vehicle.isAvailable()
            );
            return "Vehicle has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add vehicle: " + e.getMessage();
        }
    }

    /**
     * Updates a vehicle's details in the database.
     *
     * @param vehicle The vehicle object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET  color = ?, isAvailable = ? WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    vehicle.getColor(),
                    vehicle.isAvailable(),
                    vehicle.getId()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a vehicle from the database by vehicle ID.
     *
     * @param vehicleId The vehicle ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM vehicles WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, vehicleId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param vehicleId The vehicle ID to search for.
     * @return A Vehicle object if found, otherwise null.
     */
    public Vehicle getVehicleById(int vehicleId) {
        String query = "SELECT * FROM vehicles WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractVehicleFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all vehicles from the database.
     *
     * @return A list of Vehicle objects.
     */
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    /**
     * Retrieves all available vehicles from the database.
     *
     * @return A list of available Vehicle objects.
     */
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE isAvailable = true";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                availableVehicles.add(extractVehicleFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableVehicles;
    }

    /**
     * Extracts a Vehicle object from a ResultSet.
     *
     * @param rs The ResultSet containing vehicle data.
     * @return A Vehicle object.
     * @throws SQLException If a database access error occurs.
     */
   private Vehicle extractVehicleFromResultSet(ResultSet rs) throws SQLException {
    return new Vehicle(
            rs.getInt("id"),
            rs.getString("model"),
            rs.getString("brand"),
            rs.getString("color"),
            rs.getString("regNo"),
            rs.getInt("capacity"),
            rs.getBoolean("availability"),
            rs.getString("type")
    );
}


}
