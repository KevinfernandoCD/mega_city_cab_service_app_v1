package com.mycompany.megacity_cab_service.models;


public class Vehicle {
    private int vehicle_ID;
    private String model;
    private String brand;
    private String color;
    private String registrationNumber;
    private int seatingCapacity;
    private boolean availability;
    private String type;

   
    // Constructor without vehicle_ID (For cases where ID is auto-generated)
    public Vehicle(String model, String brand, String color, 
                   String registrationNumber, int seatingCapacity, boolean availability, String type) {
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.seatingCapacity = seatingCapacity;
        this.availability = availability;
        this.type = type;
    }
    
     // Constructor with vehicle_ID
    public Vehicle(int vehicle_ID, String model, String brand, String color, 
                   String registrationNumber, int seatingCapacity, boolean availability, String type) {
        this.vehicle_ID = vehicle_ID;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.seatingCapacity = seatingCapacity;
        this.availability = availability;
        this.type = type;
    }


    // Getters
    public int  getId() {
        return vehicle_ID;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getCapacity() {
        return seatingCapacity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setVehicleID(int vehicle_ID) {
        this.vehicle_ID = vehicle_ID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_ID='" + vehicle_ID + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", availability=" + availability +
                ", type='" + type + '\'' +
                '}';
    }
}
