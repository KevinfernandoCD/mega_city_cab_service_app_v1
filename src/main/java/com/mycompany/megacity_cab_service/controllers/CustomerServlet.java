package com.mycompany.megacity_cab_service.controllers;

import com.mycompany.megacity_cab_service.CustomerService;
import com.mycompany.megacity_cab_service.models.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        try {
            customerService = new CustomerService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error initializing CustomerService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "get":
                    getCustomerById(request, response);
                    break;
                case "list":
                    listCustomers(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing GET request", e);
        }
    }

    private void getCustomerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            response.getWriter().write(customer.toString());
        } else {
            response.getWriter().write("Customer not found.");
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Customer> customers = customerService.getAllCustomers();
        PrintWriter out = response.getWriter();
        customers.forEach(out::println);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String nic = request.getParameter("nic");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Customer customer = new Customer(name, phone, address, nic);
            String result = customerService.addCustomer(customer);
            response.getWriter().write(result);
        } catch (Exception e) {
            response.getWriter().write("Error adding customer: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String nic = request.getParameter("nic");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                customer.setName(name);
                customer.setPhoneNumber(phone);
                customer.setNIC(nic);
                customer.setAddress(address);
                boolean success = customerService.updateCustomer(customer);
                response.getWriter().write(success ? "Customer updated successfully." : "Failed to update customer.");
            } else {
                response.getWriter().write("Customer not found.");
            }
        } catch (Exception e) {
            response.getWriter().write("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("id") == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Customer ID is required for deletion.");
                return;
            }

            int customerId = Integer.parseInt(request.getParameter("id"));
            boolean success = customerService.deleteCustomer(customerId);
            response.getWriter().write(success ? "Customer deleted successfully." : "Failed to delete customer.");
        } catch (Exception e) {
            response.getWriter().write("Error deleting customer: " + e.getMessage());
        }
    }
}
