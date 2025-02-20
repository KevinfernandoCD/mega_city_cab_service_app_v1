package com.mycompany.megacity_cab_service.models;


public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String address;
    private String NIC;
    
    // Constructor
    public Customer(String customerId, String name, String phoneNumber, String address, String NIC) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.NIC = NIC;
    }
    
     public Customer(String name, String phoneNumber, String address, String NIC) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.NIC = NIC;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
    
    @Override
public String toString() {
    return "Customer{id=" + customerId +
           ", name='" + name + '\'' +
           ", nic='" + NIC + '\'' +
           ", phone='" + phoneNumber + '\'' +
           ", address='" + address + "'}";
}


}
