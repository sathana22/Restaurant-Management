package com.rest.services;

import com.rest.models.MenuItem;
import com.rest.models.OrderItem;
import com.rest.repository.OrderItemRepository;
import java.util.List;

public class OrderItemService {
    private final OrderItemRepository repository = new OrderItemRepository();
    private int nextOrderItemId = 1;
    private int nextOrderId = 1001;
    private int currentOrderId = -1;

    public void startNewOrder() {
        currentOrderId = nextOrderId++;
        nextOrderItemId = 1;
        repository.clear();
    }

    public int getCurrentOrderId() {
        if (currentOrderId == -1) {
            startNewOrder();
        }
        return currentOrderId;
    }

    public void addOrderItem(MenuItem menuItem, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        repository.save(new OrderItem(nextOrderItemId++, getCurrentOrderId(), menuItem, quantity));
    }

    public List<OrderItem> getAllOrderItems() {
        return repository.findAll();
    }

    public double getTotalBill() {
        double total = 0.0;
        for (OrderItem item : repository.findAll()) {
            total += item.getTotal();
        }
        return total;
    }

    public boolean hasItems() {
        return !repository.findAll().isEmpty();
    }
}
