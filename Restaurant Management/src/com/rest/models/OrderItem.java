package com.rest.models;

public class OrderItem {
    private final int orderItemId;
    private final int orderId;
    private final MenuItem menuItem;
    private final int quantity;

    public OrderItem(int orderItemId, int orderId, MenuItem menuItem, int quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return menuItem.getName()
                + " | Quantity: " + quantity
                + " | Unit price: Rs." + String.format("%.2f", menuItem.getPrice())
                + " | Amount: Rs." + String.format("%.2f", getTotal());
    }
}
