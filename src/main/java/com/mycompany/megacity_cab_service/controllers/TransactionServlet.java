package com.mycompany.megacity_cab_service.controllers;

import com.mycompany.megacity_cab_service.TransactionService;
import com.mycompany.megacity_cab_service.models.Transaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        try {
            transactionService = new TransactionService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error initializing TransactionService", e);
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
                    getTransactionById(request, response);
                    break;
                case "list":
                    listTransactions(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing GET request", e);
        }
    }

    private void getTransactionById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int transactionId = Integer.parseInt(request.getParameter("id"));
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            response.getWriter().write(transaction.toString());
        } else {
            response.getWriter().write("Transaction not found.");
        }
    }

    private void listTransactions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Transaction> transactions = transactionService.getAllTransactions();
        PrintWriter out = response.getWriter();
        transactions.forEach(out::println);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int billId = Integer.parseInt(request.getParameter("bill_id"));
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            String paymentMethod = request.getParameter("payment_method");

            Transaction transaction = new Transaction(billId, customerId, bookingId, amount, paymentMethod);
            String result = transactionService.addTransaction(transaction);
            response.getWriter().write(result);
        } catch (Exception e) {
            response.getWriter().write("Error adding transaction: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int billId = Integer.parseInt(request.getParameter("bill_id"));
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            String paymentMethod = request.getParameter("payment_method");

            Transaction transaction = transactionService.getTransactionById(id);
            if (transaction != null) {
                transaction.setBillId(billId);
                transaction.setCustomerId(customerId);
                transaction.setBookingId(bookingId);
                transaction.setAmount(amount);
                transaction.setPaymentMethod(paymentMethod);

                boolean success = transactionService.updateTransaction(transaction);
                response.getWriter().write(success ? "Transaction updated successfully." : "Failed to update transaction.");
            } else {
                response.getWriter().write("Transaction not found.");
            }
        } catch (Exception e) {
            response.getWriter().write("Error updating transaction: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("id") == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Transaction ID is required for deletion.");
                return;
            }

            int transactionId = Integer.parseInt(request.getParameter("id"));
            boolean success = transactionService.deleteTransaction(transactionId);
            response.getWriter().write(success ? "Transaction deleted successfully." : "Failed to delete transaction.");
        } catch (Exception e) {
            response.getWriter().write("Error deleting transaction: " + e.getMessage());
        }
    }
}
