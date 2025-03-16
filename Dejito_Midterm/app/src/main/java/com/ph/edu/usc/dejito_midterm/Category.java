package com.ph.edu.usc.dejito_midterm;

public class Category {
    private String name;
    private String description;
    private int imageResource; // Resource ID for the category image

    public Category(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}