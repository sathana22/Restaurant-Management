Restaurant Management (Java CLI)

Features
- Main menu: Table Management (1), Order Management (2), Exit (0)
- Table Management lists the available tables.
- Order Management lets a customer choose food, enter quantity, and see item totals and a final bill.
- Uses only two CLI classes: TableCLI and OrderCLI.
- Order IDs are generated in memory starting at 1001.
- Sample menu prices are editable in MenuItemRepository.java.

Requirements
- JDK installed
- Run commands from the "Restaurant Management" folder.

Compile:
javac -d out -sourcepath src src/com/rest/Main.java

Run:
java -cp out com.rest.Main

Notes
- This demo stores data in memory only; orders are not saved after the application exits.
- Table status is currently displayed by the existing Table model and is not automatically changed when an order is placed.
