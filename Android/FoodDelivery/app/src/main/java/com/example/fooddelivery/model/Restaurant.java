package com.example.fooddelivery;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int id;
    private String name;
    private String category;
    private double rating;
    private int imageResId;
    private String deliveryTime;
    private double deliveryFee;

    public Restaurant(int id, String name, String category, double rating, int imageResId, String deliveryTime, double deliveryFee) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.imageResId = imageResId;
        this.deliveryTime = deliveryTime;
        this.deliveryFee = deliveryFee;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getRating() {
        return rating;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }
}
