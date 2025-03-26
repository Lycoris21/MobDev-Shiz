package com.ph.edu.usc.dejito_midterm;

public class Category {
    private String name;
    private String description;
    private String imageResource;

    public Category(String name, String description, String imageResource) {
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
        return App.getContext().getResources().getIdentifier(imageResource, "drawable", App.getContext().getPackageName());
    }
}
