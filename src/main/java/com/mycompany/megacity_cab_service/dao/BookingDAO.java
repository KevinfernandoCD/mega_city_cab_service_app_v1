package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.Booking;
import com.mycompany.megacity_cab_service.utils.BookingResponseDto;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing booking-related database operations.
 */
public class BookingDAO {

    private final DbConnection dbConnection;

    public BookingDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new booking into the database.
     *
     * @param booking The booking to insert.
     * @return A message indicating success or failure.
     */
    public String insertBooking(Booking booking) {
        String query = "INSERT INTO bookings (customer_id, driver_id, vehicle_id, pickup_location, drop_location, status, distance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    booking.getCustomerId(),
                    booking.getDriverId(),
                    booking.getVehicleId(),
                    booking.getPickupLocation(),
                    booking.getDropLocation(),
                    booking.getStatus(),
                    booking.getDistance()
            );
            return "Booking has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add booking: " + e.getMessage();
        }
    }

    /**
     * Updates a booking's details in the database.
     *
     * @param booking The booking object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateBooking(Booking booking) {
        String query = "UPDATE bookings SET customer_id = ?, driver_id = ?, vehicle_id = ?, pickup_location = ?, drop_location = ?, booking_date = ?, status = ? WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    booking.getCustomerId(),
                    booking.getDriverId(),
                    booking.getVehicleId(),
                    booking.getPickupLocation(),
                    booking.getDropLocation(),
                    Timestamp.valueOf(booking.getBookingDate()),
                    booking.getStatus(),
                    booking.getId()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a booking from the database by booking ID.
     *
     * @param bookingId The booking ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, bookingId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId The booking ID to search for.
     * @return A Booking object if found, otherwise null.
     */
    public Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM bookings WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractBookingFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public BookingResponseDto getBookingByIdWithJoins(int bookingId) {
     String query = "SELECT " +
               "b.id, " +
               "c.name AS customer_name, " +
               "d.name AS driver_name, " +
               "v.regNo AS vehicle_reg_no, " +
               "v.type AS vehicle_type, " +
               "b.pickup_location, " +
               "b.drop_location, " +
               "b.booking_date, " +
               "b.status, " +
               "b.distance " +
               "FROM bookings b " +
               "JOIN customers c ON b.customer_id = c.customer_id " +
               "JOIN drivers d ON b.driver_id = d.id " +
               "JOIN vehicles v ON b.vehicle_id = v.id " +
               "WHERE b.id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return BookingResponseDto.extractBookingResponseFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of Booking objects.
     */
  public List<BookingResponseDto> getAllBookings() {
    List<BookingResponseDto> bookings = new ArrayList<>();
    String query = "SELECT " +
                   "b.id, " +
                   "c.name AS customer_name, " +
                   "d.name AS driver_name, " +
                   "v.regNo AS vehicle_reg_no, " +
                   "b.pickup_location, " +
                   "b.drop_location, " +
                   "b.booking_date, " +
                   "b.status, " +
                   "b.distance " +
                   "FROM bookings b " +
                   "JOIN customers c ON b.customer_id = c.customer_id " +
                   "JOIN drivers d ON b.driver_id = d.id " +
                   "JOIN vehicles v ON b.vehicle_id = v.id";

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            bookings.add(BookingResponseDto.extractBookingResponseFromResultSet(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return bookings;
}

    /**
     * Extracts a Booking object from a ResultSet.
     *
     * @param rs The ResultSet containing booking data.
     * @return A Booking object.
     * @throws SQLException If a database access error occurs.
     */
    private Booking extractBookingFromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setCustomerId(rs.getInt("customer_id"));
        booking.setDriverId(rs.getInt("driver_id"));
        booking.setVehicleId(rs.getInt("vehicle_id"));
        booking.setPickupLocation(rs.getString("pickup_location"));
        booking.setDropLocation(rs.getString("drop_location"));
        booking.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());
        booking.setStatus(rs.getString("status"));
        return booking;
    }
}
