package com.ph.edu.usc.dejito_midterm;

import java.util.List;

public class Cleaner {
    private String name;
    private int age;
    private String gender;
    private String details;
    private String sched;
    private String address;
    private String number;
    private float rating;
    private int imageResource;
    private List<String> services;

    int cleanRate;
    int attitudeRate;
    int satisfactionRate;

    public Cleaner(String name, int age, String gender, String details, String sched, String address, String number, float rating, int imageResource, List<String> services, int cleanRate, int attitudeRate, int satisfactionRate) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.details = details;
        this.sched = sched;
        this.address = address;
        this.number = number;
        this.rating = rating;
        this.imageResource = imageResource;
        this.services = services;

        this.cleanRate = cleanRate;
        this.attitudeRate = attitudeRate;
        this.satisfactionRate = satisfactionRate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDetails() {
        return details;
    }

    public String getSchedule() { return sched; }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
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

    public int getCleanRate() {
        return cleanRate;
    }

    public int getAttitudeRate() {
        return attitudeRate;
    }

    public int getSatisfactionRate() {
        return satisfactionRate;
    }
}