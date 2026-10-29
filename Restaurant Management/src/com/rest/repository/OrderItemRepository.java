package com.rest.repository;

import com.rest.models.OrderItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderItemRepository {
    private final List<OrderItem> orderItems = new ArrayList<>();

    public void save(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public List<OrderItem> findAll() {
        return Collections.unmodifiableList(orderItems);
    }

    public void clear() {
        orderItems.clear();
    }
}
