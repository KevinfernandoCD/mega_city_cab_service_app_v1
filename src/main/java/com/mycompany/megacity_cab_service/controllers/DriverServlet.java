package com.mycompany.megacity_cab_service.controllers;
import com.mycompany.megacity_cab_service.models.Driver;
import com.mycompany.megacity_cab_service.DriverService;
import com.mycompany.megacity_cab_service.models.User;
import com.mycompany.megacity_cab_service.utils.UserFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/drivers")
public class DriverServlet extends HttpServlet {
    private DriverService driverService;
    @Override
    public void init() throws ServletException {
        try {
            driverService = new DriverService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error initializing DriverService", e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract parameters from request
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = "driver";
        // Create user using UserFactory
        User user = UserFactory.createUser(username, email, password,role,phoneNo,address,true);

        // Check if user creation was successful
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error creating user.");
            return;
        }
        // Create Driver instance
        String resultMessage = driverService.addDriver((Driver) user);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (resultMessage != null && resultMessage.startsWith("Driver has been added")) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else if(resultMessage == "A user with this email already exists.") {

             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resultMessage = resultMessage != null ? "Error adding driver: " + resultMessage : "Error adding driver.";
        }
        out.println(resultMessage);
        out.close();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverId = request.getParameter("driverId");
        String action = request.getParameter("action");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (driverId != null) {
            Driver driver = driverService.findDriverById(driverId);
            if (driver != null) {
                out.println(driver);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                out.println("Driver not found with ID: " + driverId);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else if ("all".equalsIgnoreCase(action)) {
            List<Driver> allDrivers = driverService.findAllDrivers();
            if (!allDrivers.isEmpty()) {
                allDrivers.forEach(out::println);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                out.println("No drivers found.");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else if ("available".equalsIgnoreCase(action)) {
            List<Driver> availableDrivers = driverService.findAvailableDrivers();
            if (!availableDrivers.isEmpty()) {
                availableDrivers.forEach(out::println);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                out.println("No available drivers found.");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            out.println("Invalid action or driverId not specified for GET request.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverId = request.getParameter("driverId");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (driverId != null && !driverId.isEmpty()) {
            boolean deleted = driverService.removeDriver(driverId);
            if (deleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.println("Driver deleted successfully.");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Driver not found or could not be deleted.");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Driver ID is required for deletion.");
        }
        out.close();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverId = request.getParameter("driverId");

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (driverId == null || driverId.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Missing driverId parameter.");
            out.close();
            return;
        }

        // Extract form parameters from the request body 
        String phoneNo = request.getParameter("phone");
        String address = request.getParameter("address");
        String isAvailable = request.getParameter("available");

        // Find existing driver
        Driver existingDriver = driverService.findDriverById(driverId);
        if (existingDriver == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.println("Driver not found.");
            out.close();
            return;
        }
        

        // Update driver details
       
        if (phoneNo != null) existingDriver.setPhoneNo(phoneNo);
        if (address != null) existingDriver.setAddress(address);
        if (isAvailable != null) existingDriver.setAvailable("true".equals(isAvailable) ? true :false);
        

        boolean updated = driverService.updateDriver(existingDriver);
        

        if (updated) {
            response.setStatus(HttpServletResponse.SC_OK);
            out.println("Driver updated successfully.");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Failed to update driver.");
        }
        out.close();
    }
}