package com.ph.edu.usc.dejito_day1;

public class FoodItem {
    private String name;
    private int price;
    private int quantity;
    private String category;
    String details;
    private int imageResource;

    public FoodItem(String name, int price, int quantity, String category, String details, int imageResource) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.details = details;
        this.imageResource = imageResource;
    }

    // Getters
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }
    public String getDetails() { return details; }
    public int getImageResource() { return imageResource; }
}
