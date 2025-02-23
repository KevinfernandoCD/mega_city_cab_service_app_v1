package com.mycompany.megacity_cab_service.models;

public class Billing {
    private int id;
    private double totalFare;
    private double baseFare;
    private double addedDiscountAmount;
    private double addedTaxAmount;
    private boolean paid;
    private int bookingId;

    // Constructors
    public Billing() {
    }

    public Billing(int id, double baseFare, double discount, double tax, boolean paid, int bookingId) {
        this.id = id;
        this.baseFare = baseFare;
        this.paid = paid;
        this.addedDiscountAmount = discount;
        this.addedTaxAmount = tax;
        this.bookingId = bookingId;
    }
    
     public Billing( double baseFare, double discount, double tax, boolean paid, int bookingId) {
        this.baseFare = baseFare;
        this.paid = paid;
        this.addedDiscountAmount = discount;
        this.addedTaxAmount = tax;
        this.bookingId = bookingId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getAddedDiscountAmount() {
        return addedDiscountAmount;
    }

    public void setAddedDiscountAmount(double addedDiscountAmount) {
        this.addedDiscountAmount = addedDiscountAmount;
    }

    public double getAddedTaxAmount() {
        return addedTaxAmount;
    }

    public void setAddedTaxAmount(double addedTaxAmount) {
        this.addedTaxAmount = addedTaxAmount;
    }

 
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    // Method to calculate the final fare
    public void calculateTotalFare() {
        totalFare = (baseFare - addedDiscountAmount) + addedTaxAmount;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "id=" + id +
                ", totalFare=" + totalFare +
                ", baseFare=" + baseFare +
                ", addedDiscountAmount=" + addedDiscountAmount +
                ", addedTaxAmount=" + addedTaxAmount +
                ", paid=" + paid +
                ", bookingId=" + bookingId +
                '}';
    }
}
