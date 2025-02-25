package com.mycompany.megacity_cab_service.models;

import java.time.LocalDateTime;


public class Transaction {
    private int id;
    private int billId;
    private int customerId;
    private int bookingId;
    private double amount;
    private String paymentMethod;
    private LocalDateTime date;

    // Constructors
    public Transaction() {
    }

    public Transaction(int id, int billId, int customerId, int bookingId, double amount, String paymentMethod,LocalDateTime date ) {
        this.id = id;
        this.billId = billId;
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this. date = date;
    }
    
      public Transaction(int billId, int customerId, int bookingId, double amount, String paymentMethod) {
        this.billId = billId;
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
     public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", billId=" + billId +
                ", customerId=" + customerId +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
