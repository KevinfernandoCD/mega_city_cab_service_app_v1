package com.mycompany.megacity_cab_service;

import com.mycompany.megacity_cab_service.dao.VehicleDAO;
import com.mycompany.megacity_cab_service.models.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleService {

    private final VehicleDAO vehicleDAO;

    public VehicleService() throws SQLException, ClassNotFoundException {
        this.vehicleDAO = new VehicleDAO();
    }

    public String addVehicle(Vehicle vehicle) {
        return vehicleDAO.insertVehicle(vehicle);
    }

    public boolean updateVehicle(Vehicle vehicle) {
        return vehicleDAO.updateVehicle(vehicle);
    }

    public boolean removeVehicle(int vehicleId) {
        return vehicleDAO.deleteVehicle(vehicleId);
    }

    public Vehicle getVehicleById(int vehicleId) {
        return vehicleDAO.getVehicleById(vehicleId);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleDAO.getAvailableVehicles();
    }
}
