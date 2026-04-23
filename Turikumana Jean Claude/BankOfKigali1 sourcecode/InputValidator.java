package loan;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^07[0-9]{8}$");   // Rwandan format
    private static final Pattern NATIONAL_ID_PATTERN = Pattern.compile("^[0-9]{16}$");

    // Validate non-empty string
    public static String validateString(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: Input cannot be empty. Please try again.");
        }
    }


    public static double validatePositiveDouble(Scanner sc, String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Error: Value must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }


    public static int validatePositiveInt(Scanner sc, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Error: Value must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }


    public static double validateInterestRate(Scanner sc, String prompt) {
        double rate;
        while (true) {
            rate = validatePositiveDouble(sc, prompt);
            if (rate <= 100) {
                return rate;
            } else {
                System.out.println("Error: Interest rate cannot exceed 100%.");
            }
        }
    }


    public static String validatePhone(Scanner sc, String prompt) {
        String phone;
        while (true) {
            phone = validateString(sc, prompt);
            if (PHONE_PATTERN.matcher(phone).matches()) {
                return phone;
            } else {
                System.out.println("Error: Invalid phone number. Must be 10 digits starting with 07 (e.g., 0788123456).");
            }
        }
    }


    public static String validateNationalId(Scanner sc, String prompt) {
        String id;
        while (true) {
            id = validateString(sc, prompt);
            if (NATIONAL_ID_PATTERN.matcher(id).matches()) {
                return id;
            } else {
                System.out.println("Error: National ID must be exactly 16 digits.");
            }
        }
    }


    public static String validateLoanType(Scanner sc, String prompt) {
        String type;
        while (true) {
            type = validateString(sc, prompt);
            String lower = type.toLowerCase();
            if (lower.equals("personal") || lower.equals("home") || lower.equals("car") ||
                lower.equals("business") || lower.equals("student") || lower.equals("agricultural")) {
                return lower.substring(0,1).toUpperCase() + lower.substring(1);
            } else {
                System.out.println("Error: Invalid loan type. Choose: Personal, Home, Car, Business, Student, Agricultural");
            }
        }
    }


    public static String validateLoanId(Scanner sc, String prompt) {
        return validateString(sc, prompt);
    }

    // Validate loan amount range (reasonable)
    public static double validateLoanAmount(Scanner sc, String prompt) {
        double amount = validatePositiveDouble(sc, prompt);
        while (amount > 1_000_000_000) {
            System.out.println("Error: Loan amount too large (max 1,000,000,000).");
            amount = validatePositiveDouble(sc, prompt);
        }
        return amount;
    }
}