package com.mycompany.megacity_cab_service.controllers;

import com.mycompany.megacity_cab_service.VehicleService;
import com.mycompany.megacity_cab_service.models.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/vehicle")
public class VehicleServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        try {
            vehicleService = new VehicleService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error initializing VehicleService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "get":
                    getVehicleById(request, response);
                    break;
                case "list":
                    listVehicles(request, response);
                    break;
                case "available":
                    listAvailableVehicles(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing GET request", e);
        }
    }

    private void getVehicleById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int vehicleId = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle != null) {
            response.getWriter().write(vehicle.toString());
        } else {
            response.getWriter().write("Vehicle not found.");
        }
    }

    private void listVehicles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        PrintWriter out = response.getWriter();
        vehicles.forEach(out::println);
    }

    private void listAvailableVehicles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();
        response.getWriter().write(vehicles.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String color = request.getParameter("color");
            String regNo = request.getParameter("regNo");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String type = request.getParameter("type");

            Vehicle vehicle = new Vehicle(model, brand, color, regNo, capacity, true, type);
            String result = vehicleService.addVehicle(vehicle);
            response.getWriter().write(result);
        } catch (Exception e) {
            response.getWriter().write("Error adding vehicle: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

            Vehicle vehicle = vehicleService.getVehicleById(id);
            if (vehicle != null) {
                vehicle.setAvailability(availability);
                boolean success = vehicleService.updateVehicle(vehicle);
                response.getWriter().write(success ? "Vehicle updated successfully." : "Failed to update vehicle.");
            } else {
                response.getWriter().write("Vehicle not found.");
            }
        } catch (Exception e) {
            response.getWriter().write("Error updating vehicle: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getParameter("id") == null)  {
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                  response.getWriter().println("Driver ID is required for deletion.");
            }
            
         
            
            int vehicleId = Integer.parseInt(request.getParameter("id"));
            
            
            boolean success = vehicleService.removeVehicle(vehicleId);
            response.getWriter().write(success ? "Vehicle deleted successfully." : "Failed to delete vehicle.");
        } catch (Exception e) {
            response.getWriter().write("Error deleting vehicle: " + e.getMessage());
        }
    }
}
