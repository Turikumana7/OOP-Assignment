package loan;

public class LoanFactory {
    public static LoanManager createLoan(String type, String loanId, double amount, double rate, int duration,
                                          String officerName, String branchLocation, String extraParam, double extraNumber) {
        switch (type.toLowerCase()) {
            case "personal":
                return new PersonalLoan(loanId, amount, rate, duration, officerName, branchLocation, extraParam);
            case "home":
                return new HomeLoan(loanId, amount, rate, duration, officerName, branchLocation, extraNumber);
            case "car":
                return new CarLoan(loanId, amount, rate, duration, officerName, branchLocation, extraParam);
            case "business":
                return new BusinessLoan(loanId, amount, rate, duration, officerName, branchLocation, extraParam);
            case "student":
                return new StudentLoan(loanId, amount, rate, duration, officerName, branchLocation, extraParam);
            case "agricultural":
                return new AgriculturalLoan(loanId, amount, rate, duration, officerName, branchLocation, extraNumber);
            default:
                throw new IllegalArgumentException("Unknown loan type: " + type);
        }
    }
}