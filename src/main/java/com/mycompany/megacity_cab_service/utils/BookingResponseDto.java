package com.mycompany.megacity_cab_service.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BookingResponseDto {
    private int id;
    private String customerName;
    private String driverName;
    private String vehicleRegNo;
    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime bookingDate;
    private String status;
    private double distance;

    // Constructors
    public BookingResponseDto() {
    }

    public BookingResponseDto(int id, String customerName, String driverName, String vehicleRegNo, 
                              String pickupLocation, String dropLocation, LocalDateTime bookingDate, String status, double distance) {
        this.id = id;
        this.customerName = customerName;
        this.driverName = driverName;
        this.vehicleRegNo = vehicleRegNo;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.bookingDate = bookingDate;
        this.status = status;
        this.distance = distance;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
      public void setDistance(double distance) {
        this.distance = distance;
    }

    // toString method
    @Override
    public String toString() {
        return "BookingResponseDto{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", driverName='" + driverName + '\'' +
                ", vehicleRegNo='" + vehicleRegNo + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropLocation='" + dropLocation + '\'' +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
    
    public static BookingResponseDto extractBookingResponseFromResultSet(ResultSet rs) throws SQLException {
    BookingResponseDto bookingResponse = new BookingResponseDto();
    bookingResponse.setId(rs.getInt("id"));
    bookingResponse.setCustomerName(rs.getString("customer_name"));
    bookingResponse.setDriverName(rs.getString("driver_name"));
    bookingResponse.setVehicleRegNo(rs.getString("vehicle_reg_no"));
    bookingResponse.setPickupLocation(rs.getString("pickup_location"));
    bookingResponse.setDropLocation(rs.getString("drop_location"));
    bookingResponse.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());
    bookingResponse.setStatus(rs.getString("status"));
    bookingResponse.setDistance(rs.getDouble("distance"));
    return bookingResponse;
}

}
