package loan;

public abstract class Loan {
    private String loanId;
    private String loanType;
    private double loanAmount;
    private double interestRate;      // annual interest rate (%)
    private int loanDurationMonths;
    private String loanStatus;        // PENDING, APPROVED, REJECTED, ACTIVE, CLOSED


    public Loan() {
        this.loanStatus = "PENDING";
    }


    public Loan(String loanId, String loanType, double loanAmount, double interestRate, int loanDurationMonths) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanDurationMonths = loanDurationMonths;
        this.loanStatus = "PENDING";
    }


    public String getLoanId() { return loanId; }
    public void setLoanId(String loanId) { this.loanId = loanId; }

    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }

    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public int getLoanDurationMonths() { return loanDurationMonths; }
    public void setLoanDurationMonths(int loanDurationMonths) { this.loanDurationMonths = loanDurationMonths; }

    public String getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String loanStatus) { this.loanStatus = loanStatus; }


    public abstract double calculateInterest();
    public abstract double calculateMonthlyInstallment();
    public abstract boolean checkEligibility();
    public abstract void approveLoan();
    public abstract void rejectLoan();
    public abstract double calculateTotalRepayment();
    public abstract String generateLoanReport();
    public abstract boolean validateLoanDetails();

    @Override
    public String toString() {
        return String.format("Loan ID: %s | Type: %s | Amount: %.2f | Rate: %.2f%% | Duration: %d months | Status: %s",
                loanId, loanType, loanAmount, interestRate, loanDurationMonths, loanStatus);
    }
}