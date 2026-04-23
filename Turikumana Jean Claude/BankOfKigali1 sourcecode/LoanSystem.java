package loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoanSystem {
    private static List<String> existingLoanIds = new ArrayList<>();
    private static List<String> existingCustomerIds = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== BANK OF KIGALI - LOAN MANAGEMENT SYSTEM =====\n");

        // ----- Input Customer Details -----
        System.out.println("--- CUSTOMER REGISTRATION ---");
        String custId = InputValidator.validateString(sc, "Customer ID: ");
        while (existingCustomerIds.contains(custId)) {
            System.out.println("Error: Customer ID already exists.");
            custId = InputValidator.validateString(sc, "Customer ID: ");
        }
        existingCustomerIds.add(custId);

        String custName = InputValidator.validateString(sc, "Customer Name: ");
        String nationalId = InputValidator.validateNationalId(sc, "National ID (16 digits): ");
        String phone = InputValidator.validatePhone(sc, "Phone Number (e.g., 0788123456): ");

        Customer customer = new Customer(custId, custName, nationalId, phone);

        // ----- Input Loan Details -----
        System.out.println("\n--- LOAN APPLICATION ---");
        String loanId = InputValidator.validateLoanId(sc, "Loan ID: ");
        while (existingLoanIds.contains(loanId)) {
            System.out.println("Error: Loan ID already exists.");
            loanId = InputValidator.validateLoanId(sc, "Loan ID: ");
        }
        existingLoanIds.add(loanId);

        String loanType = InputValidator.validateLoanType(sc, "Loan Type (Personal/Home/Car/Business/Student/Agricultural): ");
        double amount = InputValidator.validateLoanAmount(sc, "Loan Amount: ");
        double rate = InputValidator.validateInterestRate(sc, "Annual Interest Rate (%): ");
        int duration = InputValidator.validatePositiveInt(sc, "Loan Duration (months): ");

        String officerName = InputValidator.validateString(sc, "Loan Officer Name: ");
        String branch = InputValidator.validateString(sc, "Branch Location: ");

        // Specific attribute based on loan type
        String extraString = "";
        double extraNumber = 0;
        switch (loanType.toLowerCase()) {
            case "personal":
                extraString = InputValidator.validateString(sc, "Employment Status: ");
                break;
            case "home":
                extraNumber = InputValidator.validatePositiveDouble(sc, "Property Value: ");
                break;
            case "car":
                extraString = InputValidator.validateString(sc, "Car Model: ");
                break;
            case "business":
                extraString = InputValidator.validateString(sc, "Business Type: ");
                break;
            case "student":
                extraString = InputValidator.validateString(sc, "University Name: ");
                break;
            case "agricultural":
                extraNumber = InputValidator.validatePositiveDouble(sc, "Farm Size (hectares): ");
                break;
        }

        // Create loan using factory
        LoanManager loan = LoanFactory.createLoan(loanType, loanId, amount, rate, duration,
                officerName, branch, extraString, extraNumber);

        // Validate loan details
        if (!loan.validateLoanDetails()) {
            System.out.println("Loan validation failed. Exiting.");
            return;
        }

        // Check eligibility and auto-approve/reject
        System.out.println("\n--- ELIGIBILITY CHECK ---");
        if (loan.checkEligibility()) {
            loan.approveLoan();
        } else {
            loan.rejectLoan();
            System.out.println("Loan application rejected. Cannot proceed with payment.");
            System.out.println(loan.generateLoanReport());
            sc.close();
            return;
        }

        // Display loan and customer info
        System.out.println("\n--- LOAN DETAILS ---");
        System.out.println(loan);
        System.out.println("\n--- CUSTOMER DETAILS ---");
        System.out.println(customer);


        System.out.println("\n--- REPAYMENT SECTION ---");
        boolean morePayments = true;
        int paymentCounter = 1;
        while (morePayments && loan.calculateRemainingBalance() > 0) {
            System.out.printf("\nRemaining balance: %.2f\n", loan.calculateRemainingBalance());
            double payment = InputValidator.validatePositiveDouble(sc, "Enter payment amount: ");
            loan.processPayment(payment);
            loan.generatePaymentReceipt(payment);


            Repayment repayment = new Repayment("PAY" + paymentCounter++, loan, payment);
            repayment.updateRemainingBalance();
            System.out.println("Repayment Record: " + repayment);

            if (loan.calculateRemainingBalance() > 0) {
                String choice = InputValidator.validateString(sc, "Make another payment? (yes/no): ");
                if (!choice.equalsIgnoreCase("yes")) {
                    morePayments = false;
                }
            } else {
                System.out.println("Loan fully repaid! Thank you.");
                morePayments = false;
            }
        }


        System.out.println("\n" + loan.generateLoanReport());
        sc.close();
    }
}