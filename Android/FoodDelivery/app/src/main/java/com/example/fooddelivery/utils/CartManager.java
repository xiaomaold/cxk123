package com.example.fooddelivery;

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

    public void addToCart(MenuItem menuItem) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getMenuItem().getId() == menuItem.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(new CartItem(menuItem, 1));
    }

    public void removeFromCart(MenuItem menuItem) {
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            if (cartItem.getMenuItem().getId() == menuItem.getId()) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                } else {
                    cartItems.remove(i);
                }
                return;
            }
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getQuantity();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public int getItemQuantity(MenuItem menuItem) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getMenuItem().getId() == menuItem.getId()) {
                return cartItem.getQuantity();
            }
        }
        return 0;
    }
}
