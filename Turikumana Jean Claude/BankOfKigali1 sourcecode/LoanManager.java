package loan;

import java.time.LocalDate;

public class LoanManager extends Loan implements Payable {
    private String officerName;
    private String branchLocation;
    private double remainingBalance;   // for payment tracking


    public LoanManager() {
        super();
    }

    public LoanManager(String loanId, String loanType, double loanAmount, double interestRate, int loanDurationMonths,
                       String officerName, String branchLocation) {
        super(loanId, loanType, loanAmount, interestRate, loanDurationMonths);
        this.officerName = officerName;
        this.branchLocation = branchLocation;
        this.remainingBalance = calculateTotalRepayment();  // initially total repayment
    }


    public String getOfficerName() { return officerName; }
    public void setOfficerName(String officerName) { this.officerName = officerName; }

    public String getBranchLocation() { return branchLocation; }
    public void setBranchLocation(String branchLocation) { this.branchLocation = branchLocation; }


    @Override
    public double calculateInterest() {
        // Simple interest: (amount * rate * duration) / (100 * 12)
        return (getLoanAmount() * getInterestRate() * getLoanDurationMonths()) / (100 * 12);
    }

    @Override
    public double calculateMonthlyInstallment() {
        double totalRepayment = calculateTotalRepayment();
        return totalRepayment / getLoanDurationMonths();
    }

    @Override
    public boolean checkEligibility() {
        // Basic eligibility: amount between 1000 and 500000, duration 3-120 months, rate 5-25%
        return getLoanAmount() >= 1000 && getLoanAmount() <= 500000 &&
               getLoanDurationMonths() >= 3 && getLoanDurationMonths() <= 120 &&
               getInterestRate() >= 5 && getInterestRate() <= 25;
    }

    @Override
    public void approveLoan() {
        if (checkEligibility()) {
            setLoanStatus("APPROVED");
            System.out.println("Loan " + getLoanId() + " has been APPROVED.");
        } else {
            System.out.println("Loan does not meet eligibility criteria. Cannot approve.");
        }
    }

    @Override
    public void rejectLoan() {
        setLoanStatus("REJECTED");
        System.out.println("Loan " + getLoanId() + " has been REJECTED.");
    }

    @Override
    public double calculateTotalRepayment() {
        return getLoanAmount() + calculateInterest();
    }

    @Override
    public String generateLoanReport() {
        return String.format("=== LOAN REPORT ===\n%s\nTotal Interest: %.2f\nTotal Repayment: %.2f\nMonthly Installment: %.2f\nOfficer: %s\nBranch: %s",
                toString(), calculateInterest(), calculateTotalRepayment(), calculateMonthlyInstallment(),
                officerName, branchLocation);
    }

    @Override
    public boolean validateLoanDetails() {
        return getLoanId() != null && !getLoanId().trim().isEmpty() &&
               getLoanType() != null && !getLoanType().trim().isEmpty() &&
               getLoanAmount() > 0 &&
               getInterestRate() > 0 &&
               getLoanDurationMonths() > 0;
    }


    @Override
    public void processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }
        if (amount > remainingBalance) {
            System.out.println("Amount exceeds remaining balance. Payment not processed.");
            return;
        }
        remainingBalance -= amount;
        System.out.printf("Payment of %.2f processed successfully.\n", amount);
        if (remainingBalance == 0) {
            setLoanStatus("CLOSED");
            System.out.println("Loan fully repaid. Status set to CLOSED.");
        }
    }

    @Override
    public double calculateRemainingBalance() {
        return remainingBalance;
    }

    @Override
    public void generatePaymentReceipt(double amount) {
        System.out.println("\n********** PAYMENT RECEIPT **********");
        System.out.println("Loan ID: " + getLoanId());
        System.out.println("Loan Type: " + getLoanType());
        System.out.println("Amount Paid: " + amount);
        System.out.println("Date: " + LocalDate.now());
        System.out.println("Remaining Balance: " + remainingBalance);
        System.out.println("*************************************\n");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Officer: %s | Branch: %s | Remaining: %.2f",
                officerName, branchLocation, remainingBalance);
    }
}