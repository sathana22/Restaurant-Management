package com.rest.repository;

import com.rest.models.MenuItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuItemRepository {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemRepository() {
        menuItems.add(new MenuItem(1, "Dosa", 50.00));
        menuItems.add(new MenuItem(2, "Idly", 30.00));
        menuItems.add(new MenuItem(3, "Pongal", 40.00));
        menuItems.add(new MenuItem(4, "Poori", 60.00));
        menuItems.add(new MenuItem(5, "Chapati", 35.00));
    }

    public List<MenuItem> findAll() {
        return Collections.unmodifiableList(menuItems);
    }

    public MenuItem findById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getMenuItemId() == id) {
                return item;
            }
        }
        return null;
    }
}
