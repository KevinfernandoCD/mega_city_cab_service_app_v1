package com.mycompany.megacity_cab_service.controllers;

import com.mycompany.megacity_cab_service.BillingService;
import com.mycompany.megacity_cab_service.models.Billing;
import com.mycompany.megacity_cab_service.utils.DiscountFare;
import com.mycompany.megacity_cab_service.utils.TaxFare;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {
    private BillingService billingService;

    @Override
    public void init() throws ServletException {
        try {
            billingService = new BillingService(new DiscountFare(), new TaxFare());
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize BillingService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String result = billingService.addBilling(bookingId);
        response.getWriter().write(result);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int billingId = Integer.parseInt(request.getParameter("billingId"));
        double baseFare = Double.parseDouble(request.getParameter("baseFare"));
        double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));
        double taxAmount = Double.parseDouble(request.getParameter("taxAmount"));
        boolean paid = Boolean.parseBoolean(request.getParameter("paid"));
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

        Billing billing = new Billing(billingId, baseFare, discountAmount, taxAmount, paid, bookingId);
        boolean success = billingService.updateBilling(billing);
        response.getWriter().write(success ? "Billing updated successfully" : "Failed to update billing");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int billingId = Integer.parseInt(request.getParameter("billingId"));
        boolean success = billingService.deleteBilling(billingId);
        response.getWriter().write(success ? "Billing deleted successfully" : "Failed to delete billing");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billingIdParam = request.getParameter("billingId");
        if (billingIdParam != null) {
            int billingId = Integer.parseInt(billingIdParam);
            Billing billing = billingService.getBillingById(billingId);
            response.getWriter().write(billing != null ? billing.toString() : "Billing record not found");
        } else {
            List<Billing> billings = billingService.getAllBillingRecords();
            billings.forEach(response.getWriter()::println);
           response.setStatus(HttpServletResponse.SC_OK);

        }
    }
}
