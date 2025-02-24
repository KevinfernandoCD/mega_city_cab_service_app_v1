package com.mycompany.megacity_cab_service;

import com.mycompany.megacity_cab_service.dao.BookingDAO;
import com.mycompany.megacity_cab_service.models.Booking;
import com.mycompany.megacity_cab_service.utils.BookingResponseDto;


import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling booking-related business logic.
 */
public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService() throws SQLException, ClassNotFoundException {
        this.bookingDAO = new BookingDAO();
    }

    /**
     * Adds a new booking.
     *
     * @param booking The booking object.
     * @return A success message or error message.
     */
    public String addBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }

    /**
     * Updates an existing booking.
     *
     * @param booking The updated booking object.
     * @return true if updated successfully, false otherwise.
     */
    public boolean updateBooking(Booking booking) {
        return bookingDAO.updateBooking(booking);
    }
    /**
     * Deletes a booking by its ID.
     *
     * @param bookingId The ID of the booking to delete.
     * @return true if deletion is successful, false otherwise.
     */
    public boolean deleteBooking(int bookingId) {
        return bookingDAO.deleteBooking(bookingId);
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId The booking ID.
     * @return The Booking object if found, otherwise null.
     */
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }
    
     public BookingResponseDto getBookingByIdWithData(int bookingId) {
        return bookingDAO.getBookingByIdWithJoins(bookingId);
    }

    /**
     * Retrieves all bookings.
     *
     * @return A list of all bookings.
     */
    public List<BookingResponseDto> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}
