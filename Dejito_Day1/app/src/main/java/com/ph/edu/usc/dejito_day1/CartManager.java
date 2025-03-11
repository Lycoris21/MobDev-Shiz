package com.ph.edu.usc.dejito_day1;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public List<CartItem> getItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
