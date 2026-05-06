package com.example.fooddelivery;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int imageResId;
    private int restaurantId;

    public MenuItem(int id, String name, String description, double price, int imageResId, int restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
}
