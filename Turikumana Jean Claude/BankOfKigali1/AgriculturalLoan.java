package loan;

public class AgriculturalLoan extends LoanManager {
    private double farmSize;

    public AgriculturalLoan(String loanId, double loanAmount, double interestRate, int durationMonths,
                            String officerName, String branchLocation, double farmSize) {
        super(loanId, "Agricultural", loanAmount, interestRate, durationMonths, officerName, branchLocation);
        this.farmSize = farmSize;
    }

    public double getFarmSize() { return farmSize; }
    public void setFarmSize(double farmSize) { this.farmSize = farmSize; }

    @Override
    public double calculateInterest() {
        double base = super.calculateInterest();
        return Math.max(0, base - (getLoanAmount() * 0.015 * getLoanDurationMonths() / 12));
    }

    @Override
    public boolean checkEligibility() {
        return super.checkEligibility() && farmSize >= 0.5;
    }

    @Override
    public void approveLoan() {
        if (checkEligibility()) {
            setLoanStatus("APPROVED");
            System.out.println("Agricultural loan approved for farm size: " + farmSize + " ha");
        } else {
            System.out.println("Agricultural loan rejected. Minimum farm size is 0.5 ha.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Farm Size: " + farmSize + " ha";
    }
}