package com.rest;

import com.rest.CLI.OrderCLI;
import com.rest.CLI.PaymentCLI;
import com.rest.CLI.TableCLI;
import com.rest.services.MenuItemService;
import com.rest.services.OrderItemService;
import com.rest.services.PaymentService;
import com.rest.services.TableService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TableService tableService = new TableService();
        MenuItemService menuItemService = new MenuItemService();
        OrderItemService orderItemService = new OrderItemService();
        PaymentService paymentService = new PaymentService();

        TableCLI tableCLI = new TableCLI(tableService, scanner);
        OrderCLI orderCLI = new OrderCLI(menuItemService, orderItemService, scanner);
        PaymentCLI paymentCLI = new PaymentCLI(paymentService, scanner);

        while (true) {
            System.out.println("\n=== RESTAURANT MANAGEMENT ===");
            System.out.println("1. Table Management");
            System.out.println("2. Order Management");
            System.out.println("3. Payment Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    tableCLI.start();
                    break;
                case 2:
                    orderCLI.start();
                    break;
                case 3:
                    paymentCLI.start();
                    break;
                case 0:
                    System.out.println("Thank you for using Restaurant Management!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Choose 0, 1, 2, or 3.");
            }
        }
    }
}