package loan;

public class HomeLoan extends LoanManager {
    private double propertyValue;

    public HomeLoan(String loanId, double loanAmount, double interestRate, int durationMonths,
                    String officerName, String branchLocation, double propertyValue) {
        super(loanId, "Home", loanAmount, interestRate, durationMonths, officerName, branchLocation);
        this.propertyValue = propertyValue;
    }

    public double getPropertyValue() { return propertyValue; }
    public void setPropertyValue(double propertyValue) { this.propertyValue = propertyValue; }

    @Override
    public double calculateInterest() {
        // Home loans have 0.5% lower interest
        double base = super.calculateInterest();
        return base - (getLoanAmount() * 0.005 * getLoanDurationMonths() / 12);
    }

    @Override
    public boolean checkEligibility() {
        return super.checkEligibility() && propertyValue >= getLoanAmount() * 0.8;
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() + "\nProperty Value: " + propertyValue;
    }

    @Override
    public String toString() {
        return super.toString() + " | Property Value: " + propertyValue;
    }
}