package com.mycompany.megacity_cab_service;

import com.mycompany.megacity_cab_service.dao.TransactionsDAO;
import com.mycompany.megacity_cab_service.models.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing transaction-related business logic.
 */
public class TransactionService {
    private final TransactionsDAO transactionDAO;

    public TransactionService() throws SQLException, ClassNotFoundException {
        this.transactionDAO = new TransactionsDAO();
    }

    /**
     * Adds a new transaction to the system.
     *
     * @param transaction The transaction object to add.
     * @return A message indicating success or failure.
     */
    public String addTransaction(Transaction transaction) {
        return transactionDAO.insertTransaction(transaction);
    }

    /**
     * Updates an existing transaction.
     *
     * @param transaction The transaction object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateTransaction(Transaction transaction) {
        return transactionDAO.updateTransaction(transaction);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param transactionId The transaction ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteTransaction(int transactionId) {
        return transactionDAO.deleteTransaction(transactionId);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId The transaction ID to retrieve.
     * @return The Transaction object if found, otherwise null.
     */
    public Transaction getTransactionById(int transactionId) {
        return transactionDAO.getTransactionById(transactionId);
    }

    /**
     * Retrieves all transactions from the database.
     *
     * @return A list of all Transaction objects.
     */
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
}
