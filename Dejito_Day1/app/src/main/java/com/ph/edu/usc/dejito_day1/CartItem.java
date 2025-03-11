package com.ph.edu.usc.dejito_day1;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private int price;
    private int quantity;
    private String imagePath;

    public CartItem(String name, int price, int quantity, String imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    // Getters
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getImagePath() { return imagePath; }
}
