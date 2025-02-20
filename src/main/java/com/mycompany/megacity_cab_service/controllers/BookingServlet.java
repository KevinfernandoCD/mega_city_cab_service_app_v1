package com.mycompany.megacity_cab_service.controllers;

import com.mycompany.megacity_cab_service.BookingService;
import com.mycompany.megacity_cab_service.models.Booking;
import com.mycompany.megacity_cab_service.utils.BookingResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        try {
            bookingService = new BookingService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error initializing BookingService", e);
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
                    getBookingById(request, response);
                    break;
                case "list":
                    listBookings(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing GET request", e);
        }
    }

    private void getBookingById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookingId = Integer.parseInt(request.getParameter("id"));
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            response.getWriter().write(booking.toString());
        } else {
            response.getWriter().write("Booking not found.");
        }
    }

    private void listBookings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<BookingResponseDto> bookings = bookingService.getAllBookings();
        PrintWriter out = response.getWriter();
        bookings.forEach(out::println);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            int driverId = Integer.parseInt(request.getParameter("driver_id"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
            String pickupLocation = request.getParameter("pickup_location");
            String dropLocation = request.getParameter("drop_location");

            Booking booking = new Booking(customerId, driverId, vehicleId, pickupLocation, dropLocation, "PENNDINNG");
            String result = bookingService.addBooking(booking);
            response.getWriter().write(result);
        } catch (Exception e) {
            response.getWriter().write("Error adding booking: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            int driverId = Integer.parseInt(request.getParameter("driver_id"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
            String pickupLocation = request.getParameter("pickup_location");
            String dropLocation = request.getParameter("drop_location");
            String status = request.getParameter("status");

            Booking booking = bookingService.getBookingById(id);
            if (booking != null) {
                booking.setCustomerId(customerId);
                booking.setDriverId(driverId);
                booking.setVehicleId(vehicleId);
                booking.setPickupLocation(pickupLocation);
                booking.setDropLocation(dropLocation);
                booking.setStatus(status);

                boolean success = bookingService.updateBooking(booking);
                response.getWriter().write(success ? "Booking updated successfully." : "Failed to update booking.");
            } else {
                response.getWriter().write("Booking not found.");
            }
        } catch (Exception e) {
            response.getWriter().write("Error updating booking: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("id") == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Booking ID is required for deletion.");
                return;
            }

            int bookingId = Integer.parseInt(request.getParameter("id"));
            boolean success = bookingService.deleteBooking(bookingId);
            response.getWriter().write(success ? "Booking deleted successfully." : "Failed to delete booking.");
        } catch (Exception e) {
            response.getWriter().write("Error deleting booking: " + e.getMessage());
        }
    }
}
