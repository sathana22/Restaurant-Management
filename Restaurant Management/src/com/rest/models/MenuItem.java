package com.rest.models;

public class MenuItem {
    private final int menuItemId;
    private final String name;
    private final double price;

    public MenuItem(int menuItemId, String name, double price) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return menuItemId + ". " + name + " - Rs." + String.format("%.2f", price);
    }
}
