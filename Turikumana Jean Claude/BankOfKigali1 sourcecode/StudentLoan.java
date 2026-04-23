package loan;

public class StudentLoan extends LoanManager {
    private String universityName;

    public StudentLoan(String loanId, double loanAmount, double interestRate, int durationMonths,
                       String officerName, String branchLocation, String universityName) {
        super(loanId, "Student", loanAmount, interestRate, durationMonths, officerName, branchLocation);
        this.universityName = universityName;
    }

    public String getUniversityName() { return universityName; }
    public void setUniversityName(String universityName) { this.universityName = universityName; }

    @Override
    public double calculateInterest() {
        // Student loans have 2% lower interest
        double base = super.calculateInterest();
        return Math.max(0, base - (getLoanAmount() * 0.02 * getLoanDurationMonths() / 12));
    }

    @Override
    public boolean checkEligibility() {
        return super.checkEligibility() && getLoanAmount() <= 20000 && universityName != null;
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() + "\nUniversity: " + universityName;
    }

    @Override
    public String toString() {
        return super.toString() + " | University: " + universityName;
    }
}