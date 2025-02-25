package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.Transaction;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing transaction-related database operations.
 */
public class TransactionsDAO {

    private final DbConnection dbConnection;

    public TransactionsDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new transaction into the database.
     *
     * @param transaction The transaction to insert.
     * @return A message indicating success or failure.
     */
    public String insertTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (bill_id, customer_id, booking_id, amount, payment_method) VALUES (?, ?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    transaction.getBillId(),
                    transaction.getCustomerId(),
                    transaction.getBookingId(),
                    transaction.getAmount(),
                    transaction.getPaymentMethod()
            );
            return "Transaction has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add transaction: " + e.getMessage();
        }
    }

    /**
     * Updates an existing transaction in the database.
     *
     * @param transaction The transaction object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateTransaction(Transaction transaction) {
        String query = "UPDATE transactions SET bill_id = ?, customer_id = ?, booking_id = ?, amount = ?, payment_method = ? WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    transaction.getBillId(),
                    transaction.getCustomerId(),
                    transaction.getBookingId(),
                    transaction.getAmount(),
                    transaction.getPaymentMethod(),
                    transaction.getId()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a transaction from the database by transaction ID.
     *
     * @param transactionId The transaction ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteTransaction(int transactionId) {
        String query = "DELETE FROM transactions WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, transactionId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId The transaction ID to search for.
     * @return A Transaction object if found, otherwise null.
     */
    public Transaction getTransactionById(int transactionId) {
        String query = "SELECT * FROM transactions WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, transactionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractTransactionFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all transactions from the database.
     *
     * @return A list of Transaction objects.
     */
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                transactions.add(extractTransactionFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Extracts a Transaction object from a ResultSet.
     *
     * @param rs The ResultSet containing transaction data.
     * @return A Transaction object.
     * @throws SQLException If a database access error occurs.
     */
    private Transaction extractTransactionFromResultSet(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setBillId(rs.getInt("bill_id"));
        transaction.setCustomerId(rs.getInt("customer_id"));
        transaction.setBookingId(rs.getInt("booking_id"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setPaymentMethod(rs.getString("payment_method"));
        transaction.setDate(rs.getTimestamp("createdAt").toLocalDateTime());
        return transaction;
    }
}
