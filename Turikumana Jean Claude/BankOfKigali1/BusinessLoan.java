package loan;

public class BusinessLoan extends LoanManager {
    private String businessType;

    public BusinessLoan(String loanId, double loanAmount, double interestRate, int durationMonths,
                        String officerName, String branchLocation, String businessType) {
        super(loanId, "Business", loanAmount, interestRate, durationMonths, officerName, branchLocation);
        this.businessType = businessType;
    }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }

    @Override
    public double calculateInterest() {

        double base = super.calculateInterest();
        return base - (getLoanAmount() * 0.0075 * getLoanDurationMonths() / 12);
    }

    @Override
    public boolean checkEligibility() {
        return super.checkEligibility() && businessType != null && !businessType.trim().isEmpty();
    }

    @Override
    public void rejectLoan() {
        setLoanStatus("REJECTED");
        System.out.println("Business loan rejected. Reason: Business type not supported or eligibility failed.");
    }

    @Override
    public String toString() {
        return super.toString() + " | Business Type: " + businessType;
    }
}