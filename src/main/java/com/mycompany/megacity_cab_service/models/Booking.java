package com.mycompany.megacity_cab_service.models;

import java.time.LocalDateTime;


public class Booking {
    private int id;
    private int customerId;
    private int driverId;
    private int vehicleId;
    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime bookingDate;
    private String status;

    // Constructors
    public Booking() {
    }

    public Booking(int id , int customerId, int driverId, int vehicleId, String pickupLocation, String dropLocation, String status) {
        this.id = id;
        this.customerId = customerId;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.bookingDate = LocalDateTime.now();
        this.status = status;
    }

    public Booking(int customerId, int driverId, int vehicleId, String pickupLocation, String dropLocation, String status) {
        this.customerId = customerId;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = status;
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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

    // toString method
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", driverId=" + driverId +
                ", vehicleId=" + vehicleId +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropLocation='" + dropLocation + '\'' +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                '}';
    }
}
