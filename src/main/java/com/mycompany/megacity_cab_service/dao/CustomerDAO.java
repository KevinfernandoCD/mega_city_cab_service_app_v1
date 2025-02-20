package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.Customer;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing customer-related database operations.
 */
public class CustomerDAO {

    private final DbConnection dbConnection;

    public CustomerDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new customer into the database.
     *
     * @param customer The customer to insert.
     * @return A message indicating success or failure.
     */
    public String insertCustomer(Customer customer) {
        String query = "INSERT INTO customers (customer_id, name, phone, address, NIC) VALUES (?, ?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getNIC()
            );
            return "Customer has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add customer: " + e.getMessage();
        }
    }

    /**
     * Updates a customer's details in the database.
     *
     * @param customer The customer object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customers SET name = ?, phone = ?, address = ?, nic = ? WHERE customer_id = ?";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    customer.getName(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getNIC(),
                    customer.getCustomerId()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a customer from the database by customer ID.
     *
     * @param customerId The customer ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteCustomer(int customerId) {
        String query = "DELETE FROM customers WHERE customerId = ?";
        try {
            dbConnection.executeWriteQuery(query, customerId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a customer by their customer ID.
     *
     * @param customerId The customer ID to search for.
     * @return A Customer object if found, otherwise null.
     */
    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractCustomerFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of Customer objects.
     */
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(extractCustomerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Extracts a Customer object from a ResultSet.
     *
     * @param rs The ResultSet containing customer data.
     * @return A Customer object.
     * @throws SQLException If a database access error occurs.
     */
    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getString("customer_id"),
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("NIC")
        );
    }
}
