package loan;

public class CarLoan extends LoanManager {
    private String carModel;

    public CarLoan(String loanId, double loanAmount, double interestRate, int durationMonths,
                   String officerName, String branchLocation, String carModel) {
        super(loanId, "Car", loanAmount, interestRate, durationMonths, officerName, branchLocation);
        this.carModel = carModel;
    }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    @Override
    public double calculateMonthlyInstallment() {
        // Car loans have a shorter maximum duration, so installment may be higher
        double total = calculateTotalRepayment();
        return total / getLoanDurationMonths();
    }

    @Override
    public boolean checkEligibility() {
        return super.checkEligibility() && getLoanDurationMonths() <= 60;
    }

    @Override
    public void approveLoan() {
        if (checkEligibility()) {
            setLoanStatus("APPROVED");
            System.out.println("Car loan approved for model: " + carModel);
        } else {
            System.out.println("Car loan rejected (duration > 60 months or other criteria).");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Car Model: " + carModel;
    }
}