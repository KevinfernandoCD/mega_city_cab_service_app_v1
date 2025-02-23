package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.Billing;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing billing-related database operations.
 */
public class BillingDAO {

    private final DbConnection dbConnection;

    public BillingDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new billing record into the database.
     *
     * @param billing The billing object to insert.
     * @return A message indicating success or failure.
     */
    public String insertBilling(Billing billing) {
        String query = "INSERT INTO Billing (base_fare, total_fare, added_discount_amount, added_tax_amount, paid, booking_id) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    billing.getBaseFare(),
                    billing.getTotalFare(),
                    billing.getAddedDiscountAmount(),
                    billing.getAddedTaxAmount(),
                    billing.isPaid(),
                    billing.getBookingId()
            );
            return "Billing record has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add billing record: " + e.getMessage();
        }
    }

    /**
     * Updates a billing record in the database.
     *
     * @param billing The billing object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateBilling(Billing billing) {
        String query = "UPDATE Billing SET base_fare = ?, total_fare = ?, added_discount_amount = ?, " +
                       "added_tax_amount = ?, paid = ?, booking_id = ? WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(
                    query,
                    billing.getBaseFare(),
                    billing.getTotalFare(),
                    billing.getAddedDiscountAmount(),
                    billing.getAddedTaxAmount(),
                    billing.isPaid(),
                    billing.getBookingId(),
                    billing.getId()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a billing record from the database by billing ID.
     *
     * @param billingId The billing ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteBilling(int billingId) {
        String query = "DELETE FROM Billing WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, billingId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a billing record by its ID.
     *
     * @param billingId The billing ID to search for.
     * @return A Billing object if found, otherwise null.
     */
    public Billing getBillingById(int billingId) {
        String query = "SELECT * FROM Billing WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, billingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractBillingFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all billing records from the database.
     *
     * @return A list of Billing objects.
     */
    public List<Billing> getAllBillingRecords() {
        List<Billing> billingList = new ArrayList<>();
        String query = "SELECT * FROM Billing";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                billingList.add(extractBillingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billingList;
    }

    /**
     * Extracts a Billing object from a ResultSet.
     *
     * @param rs The ResultSet containing billing data.
     * @return A Billing object.
     * @throws SQLException If a database access error occurs.
     */
    private Billing extractBillingFromResultSet(ResultSet rs) throws SQLException {
        return new Billing(
                rs.getInt("id"),
                rs.getDouble("base_fare"),
                rs.getDouble("added_discount_amount"),
                rs.getDouble("added_tax_amount"),
                rs.getBoolean("paid"),
                rs.getInt("booking_id")
        );
    }
}
