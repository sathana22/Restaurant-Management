package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.models.OrderItem;
import com.rest.services.MenuItemService;
import com.rest.services.OrderItemService;
import java.util.Scanner;

public class OrderCLI {
    private final MenuItemService menuService;
    private final OrderItemService orderService;
    private final Scanner scanner;

    public OrderCLI(MenuItemService menuService, OrderItemService orderService, Scanner scanner) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void start() {
        orderService.startNewOrder();

        while (true) {
            System.out.println("\n=== ORDER MANAGEMENT ===");
            System.out.println("Order ID: " + orderService.getCurrentOrderId());
            System.out.println("--- MENU ---");
            for (MenuItem item : menuService.getAllMenuItems()) {
                System.out.println(item);
            }
            System.out.println("0. Finish order");
            System.out.print("Choose a food item: ");

            int choice = readInt();
            if (choice == Integer.MIN_VALUE) {
                continue;
            }
            if (choice == 0) {
                break;
            }

            MenuItem selectedItem;
            switch (choice) {
                case 1:
                    selectedItem = menuService.getMenuItemById(1);
                    break;
                case 2:
                    selectedItem = menuService.getMenuItemById(2);
                    break;
                case 3:
                    selectedItem = menuService.getMenuItemById(3);
                    break;
                case 4:
                    selectedItem = menuService.getMenuItemById(4);
                    break;
                case 5:
                    selectedItem = menuService.getMenuItemById(5);
                    break;
                default:
                    System.out.println("Invalid menu choice. Please choose an item from the menu.");
                    continue;
            }

            System.out.println("Selected item: " + selectedItem.getName());
            System.out.println("Price: Rs." + String.format("%.2f", selectedItem.getPrice()));
            System.out.print("Enter quantity: ");
            int quantity = readInt();

            if (quantity <= 0) {
                if (quantity != Integer.MIN_VALUE) {
                    System.out.println("Quantity must be greater than zero.");
                }
                continue;
            }

            orderService.addOrderItem(selectedItem, quantity);
            System.out.println("Added successfully. Amount: Rs."
                    + String.format("%.2f", selectedItem.getPrice() * quantity));
        }

        printBill();
    }

    private int readInt() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return Integer.MIN_VALUE;
        }
    }

    private void printBill() {
        System.out.println("\n=== FINAL BILL ===");
        System.out.println("Order ID: " + orderService.getCurrentOrderId());

        if (!orderService.hasItems()) {
            System.out.println("No items were ordered.");
        } else {
            for (OrderItem item : orderService.getAllOrderItems()) {
                System.out.println(item);
            }
            System.out.println("------------------------------");
            System.out.println("Total bill: Rs." + String.format("%.2f", orderService.getTotalBill()));
        }
        System.out.println("Returning to main menu...");
    }
}
