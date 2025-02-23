import interfaces.FareStrategy;

public class Billing {
    private int billingId;
    private int bookingId;
    private int customerId;
    private double totalFare;
    private double discount;
    private double addedDiscounts; 
    private double taxAmount;
    private double specialCharges;
    private double baseFare;
    private boolean paid;

    public Billing(int billingId, int bookingId, int customerId, double baseFare, boolean paid) {
        this.billingId = billingId;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.baseFare = baseFare;
        this.specialCharges = specialCharges;
        this.paid = paid;
        this.addedDiscounts = 0.0;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double input) {
        this.discount = input;
    }

    public double getAddedDiscounts() {
        return addedDiscounts;
    }

    public void setAddedDiscounts(double addedDiscounts) {
        this.addedDiscounts = addedDiscounts;
    }

    public void setTaxAmount(double amount) {
        this.taxAmount = amount;
    }

    public void calculateTotalFare(FareStrategy strategy, double distance) {
        this.totalFare = strategy.calculateFare(baseFare, distance) + specialCharges - addedDiscounts;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
