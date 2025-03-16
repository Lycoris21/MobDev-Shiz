package com.ph.edu.usc.dejito_midterm;

import java.util.List;

public class Cleaner {
    private String name;
    private String gender;
    private String details;
    private float rating;
    private int imageResource; // Resource ID for the cleaner's image
    private List<String> services; // List of services offered by the cleaner

    public Cleaner(String name, String gender, String details, float rating, int imageResource, List<String> services) {
        this.name = name;
        this.gender = gender;
        this.details = details;
        this.rating = rating;
        this.imageResource = imageResource;
        this.services = services;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDetails() {
        return details;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResource() {
        return imageResource;
    }

    public List<String> getCategory() {
        return services;
    }
}