package com.mycompany.megacity_cab_service;

import com.mycompany.megacity_cab_service.dao.BillingDAO;
import com.mycompany.megacity_cab_service.models.Billing;
import com.mycompany.megacity_cab_service.models.Booking;
import com.mycompany.megacity_cab_service.utils.BookingResponseDto;
import com.mycompany.megacity_cab_service.utils.CarBasefareStrategy;
import com.mycompany.megacity_cab_service.utils.DbConnection;
import com.mycompany.megacity_cab_service.utils.DiscountFare;
import com.mycompany.megacity_cab_service.utils.TaxFare;
import com.mycompany.megacity_cab_service.utils.VanBasefareStrategy;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing billing operations.
 */
public class BillingService {

    private final BillingDAO billingDAO;
    private final DiscountFare discountStrategy;
    private final TaxFare taxStrategy;
    private final BookingService bookingService;

    public BillingService(DiscountFare discountStrategy, TaxFare taxStrategy) throws SQLException, ClassNotFoundException {
        this.billingDAO = new BillingDAO();
        this.discountStrategy = discountStrategy;
        this.taxStrategy = taxStrategy;
        this.bookingService = new BookingService();
    }

    public String addBilling(int bookingId) {
        
        BookingResponseDto booking = this.bookingService.getBookingByIdWithData(bookingId);
        
        double distance = booking.getDistance();
        
        String vehicleType = booking.getvehicleType();
        
        double baseAmount = 0;
                
        if("Car".equals(vehicleType)) { 
            
            baseAmount = new CarBasefareStrategy().calculateBasefare(distance);
            
        }else if("Van".equals(vehicleType)) {
            
             baseAmount = new VanBasefareStrategy().calculateBasefare(distance);

        }
        
        // Apply discount and tax strategies
        double discountAmount = discountStrategy.calculateFare(baseAmount, distance);
        double taxAmount = taxStrategy.calculateFare(baseAmount, distance);

        // Calculate total fare
        double totalFare = (baseAmount - discountAmount) + taxAmount;

        // Create Billing object
        Billing billing = new Billing(baseAmount, discountAmount, taxAmount, false, bookingId);
        billing.setTotalFare(totalFare);

        return billingDAO.insertBilling(billing);
    }

    /**
     * Updates an existing billing record in the database.
     *
     * @param billing The Billing object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateBilling(Billing billing) {
        return billingDAO.updateBilling(billing);
    }

    /**
     * Deletes a billing record from the database by billing ID.
     *
     * @param billingId The billing ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteBilling(int billingId) {
        return billingDAO.deleteBilling(billingId);
    }

    /**
     * Retrieves a billing record by its ID.
     *
     * @param billingId The billing ID to search for.
     * @return A Billing object if found, otherwise null.
     */
    public Billing getBillingById(int billingId) {
        return billingDAO.getBillingById(billingId);
    }

    /**
     * Retrieves all billing records from the database.
     *
     * @return A list of Billing objects.
     */
    public List<Billing> getAllBillingRecords() {
        return billingDAO.getAllBillingRecords();
    }
}
