package loan;

public class PersonalLoan extends LoanManager {
    private String employmentStatus;

    public PersonalLoan(String loanId, double loanAmount, double interestRate, int durationMonths,
                        String officerName, String branchLocation, String employmentStatus) {
        super(loanId, "Personal", loanAmount, interestRate, durationMonths, officerName, branchLocation);
        this.employmentStatus = employmentStatus;
    }

    public String getEmploymentStatus() { return employmentStatus; }
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }


    @Override
    public double calculateInterest() {
        // Personal loans have 1% higher interest
        double base = super.calculateInterest();
        return base + (getLoanAmount() * 0.01 * getLoanDurationMonths() / 12);
    }

    @Override
    public boolean checkEligibility() {
        return super.checkEligibility() && employmentStatus != null && !employmentStatus.trim().isEmpty();
    }

    @Override
    public void approveLoan() {
        if (checkEligibility()) {
            setLoanStatus("APPROVED");
            System.out.println("Personal loan approved for employee status: " + employmentStatus);
        } else {
            System.out.println("Personal loan rejected due to eligibility failure.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Employment: " + employmentStatus;
    }
}