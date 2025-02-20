package com.mycompany.megacity_cab_service;

import com.mycompany.megacity_cab_service.dao.CustomerDAO;
import com.mycompany.megacity_cab_service.models.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing customer-related operations.
 */
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService() throws SQLException, ClassNotFoundException {
        this.customerDAO = new CustomerDAO();
    }

    /**
     * Adds a new customer.
     *
     * @param customer The customer to add.
     * @return A message indicating success or failure.
     */
    public String addCustomer(Customer customer) {
        return customerDAO.insertCustomer(customer);
    }

    /**
     * Updates an existing customer's details.
     *
     * @param customer The customer object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param customerId The customer ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The customer ID to search for.
     * @return A Customer object if found, otherwise null.
     */
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    /**
     * Retrieves all customers.
     *
     * @return A list of Customer objects.
     */
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
