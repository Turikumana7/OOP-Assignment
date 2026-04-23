package loan;

import java.time.LocalDate;

public class Repayment {
    private String paymentId;
    private LoanManager loan;
    private double amountPaid;
    private LocalDate paymentDate;
    private double remainingBalance;

    public Repayment(String paymentId, LoanManager loan, double amountPaid) {
        this.paymentId = paymentId;
        this.loan = loan;
        this.amountPaid = amountPaid;
        this.paymentDate = LocalDate.now();
        this.remainingBalance = loan.calculateRemainingBalance();
    }

    public void updateRemainingBalance() {
        this.remainingBalance = loan.calculateRemainingBalance();
    }

    public String getPaymentId() { return paymentId; }
    public LoanManager getLoan() { return loan; }
    public double getAmountPaid() { return amountPaid; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public double getRemainingBalance() { return remainingBalance; }

    @Override
    public String toString() {
        return String.format("Payment ID: %s | Amount: %.2f | Date: %s | Remaining Balance: %.2f",
                paymentId, amountPaid, paymentDate, remainingBalance);
    }
}