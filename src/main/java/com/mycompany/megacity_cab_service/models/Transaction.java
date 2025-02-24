package com.mycompany.megacity_cab_service.models;


public class Transaction {
    private int id;
    private int billId;
    private int customerId;
    private int bookingId;
    private double amount;
    private String paymentMethod;

    // Constructors
    public Transaction() {
    }

    public Transaction(int id, int billId, int customerId, int bookingId, double amount, String paymentMethod) {
        this.id = id;
        this.billId = billId;
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", billId=" + billId +
                ", customerId=" + customerId +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
