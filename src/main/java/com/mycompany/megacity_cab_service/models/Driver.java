package com.mycompany.megacity_cab_service.models;

public class Driver extends User {
    private String phoneNo;
    private String address;
    private boolean isAvailable;
    private int userId;
    private int id;
    
    // Constructor
    public Driver( String username, String email, String password, String phoneNo, String address, boolean isAvailable) {
        super( username, email, password, "driver"); // Calling the superclass constructor
        this.phoneNo = phoneNo;
        this.address = address;
        this.isAvailable = isAvailable;
    }
    
      public Driver(int id,int userId, String username, String phoneNo, String address, boolean isAvailable) {
        super(username, "", "", "driver");
        this.phoneNo = phoneNo;
        this.address = address;
        this.isAvailable = isAvailable;
        this.id = id;
        this.userId = userId;
    }
      

   

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Toggle availability status
    public void toggleAvailability() {
        this.isAvailable = !this.isAvailable;
    }
    
   public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    
    public void setUserId(int id) {
        this.userId = id;
    }

    public int getUserId() {
        return this.userId;
    }


    @Override
    public String toString() {
        return "Driver{" +
                "id=" + getId() + // Using superclass method
                ", username='" + getUsername() + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
