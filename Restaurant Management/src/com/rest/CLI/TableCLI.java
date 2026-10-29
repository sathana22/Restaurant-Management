package com.rest.CLI;

import com.rest.models.Table;
import com.rest.services.TableService;
import java.util.List;
import java.util.Scanner;

public class TableCLI {
    private final TableService service;
    private final Scanner scanner;

    public TableCLI(TableService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== TABLE MANAGEMENT ===");
            System.out.println("1. View all tables");
            System.out.println("0. Back to main menu");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (choice == 1) {
                List<Table> tables = service.getAllTables();
                System.out.println("\n=== TABLES ===");
                for (Table table : tables) {
                    System.out.println(table);
                }
            } else if (choice == 0) {
                return;
            } else {
                System.out.println("Invalid choice. Choose 1 or 0.");
            }
        }
    }
}
