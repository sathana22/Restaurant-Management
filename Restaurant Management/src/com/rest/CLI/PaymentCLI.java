package com.rest.CLI;

import com.rest.Payment.CardPayment;
import com.rest.Payment.CashPayment;
import com.rest.Payment.PaymentMethod;
import com.rest.Payment.UPIPayment;
import com.rest.models.Payment;
import com.rest.services.PaymentService;
import java.util.List;
import java.util.Scanner;

public class PaymentCLI {
    private final PaymentService service;
    private final Scanner scanner;

    public PaymentCLI() {
        this(new PaymentService(), new Scanner(System.in));
    }

    public PaymentCLI(PaymentService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== PAYMENT MANAGEMENT ===");
            System.out.println("1. Make payment");
            System.out.println("2. View all payments");
            System.out.println("3. View payment by ID");
            System.out.println("0. Return to previous menu");

            String input = readLine("Enter your choice: ");
            if (input == null) {
                return;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    makePayment();
                    break;
                case 2:
                    viewAllPayments();
                    break;
                case 3:
                    viewPaymentById();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Choose 0, 1, 2, or 3.");
            }
        }
    }

    private void makePayment() {
        double amount;
        while (true) {
            String input = readLine("Enter payment amount (or 0 to cancel): ");
            if (input == null) {
                return;
            }

            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
                continue;
            }

            if (amount == 0) {
                System.out.println("Payment cancelled.");
                return;
            }
            if (!Double.isFinite(amount) || amount < 0) {
                System.out.println("Amount must be a finite positive number.");
                continue;
            }
            break;
        }

        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.println("0. Cancel");

        String input = readLine("Enter your choice: ");
        if (input == null) {
            return;
        }

        PaymentMethod method;
        switch (input) {
            case "1":
                method = new CashPayment();
                break;
            case "2":
                method = new CardPayment();
                break;
            case "3":
                method = new UPIPayment();
                break;
            case "0":
                System.out.println("Payment cancelled.");
                return;
            default:
                System.out.println("Invalid payment method. Payment cancelled.");
                return;
        }

        Payment payment = service.processPayment(method, amount);
        System.out.printf(
                "Payment of Rs. %.2f using %s successful. Payment ID: %d%n",
                payment.getAmount(),
                payment.getMethod(),
                payment.getId());
    }

    private void viewAllPayments() {
        List<Payment> payments = service.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        System.out.println("\n=== ALL PAYMENTS ===");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    private void viewPaymentById() {
        String input = readLine("Enter payment ID: ");
        if (input == null) {
            return;
        }

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid payment ID.");
            return;
        }

        if (id <= 0) {
            System.out.println("Payment ID must be greater than zero.");
            return;
        }

        Payment payment = service.getPaymentById(id);
        if (payment == null) {
            System.out.println("Payment with ID " + id + " was not found.");
        } else {
            System.out.println(payment);
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine().trim();
    }
}
