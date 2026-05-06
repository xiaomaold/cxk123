package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvRestaurants;
    private RestaurantAdapter adapter;
    private Button btnCart;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        setupRecyclerView();
    }

    private void initViews() {
        rvRestaurants = findViewById(R.id.rv_restaurants);
        btnCart = findViewById(R.id.btn_cart);

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void initData() {
        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(1, "川味小厨", "川菜", 4.8, 
                R.drawable.restaurant1, "30-40分钟", 5.0));
        restaurantList.add(new Restaurant(2, "粤式茶餐厅", "粤菜", 4.6, 
                R.drawable.restaurant2, "25-35分钟", 4.0));
        restaurantList.add(new Restaurant(3, "东北饺子馆", "东北菜", 4.7, 
                R.drawable.restaurant3, "35-45分钟", 6.0));
        restaurantList.add(new Restaurant(4, "日式料理", "日料", 4.9, 
                R.drawable.restaurant4, "40-50分钟", 8.0));
        restaurantList.add(new Restaurant(5, "意式披萨", "西餐", 4.5, 
                R.drawable.restaurant5, "30-40分钟", 5.0));
    }

    private void setupRecyclerView() {
        adapter = new RestaurantAdapter(restaurantList, restaurant -> {
            Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
            intent.putExtra("restaurant", restaurant);
            startActivity(intent);
        });

        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurants.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartButton();
    }

    private void updateCartButton() {
        int totalQuantity = CartManager.getInstance().getTotalQuantity();
        if (totalQuantity > 0) {
            btnCart.setVisibility(View.VISIBLE);
            btnCart.setText(String.format("购物车 (%d)", totalQuantity));
        } else {
            btnCart.setVisibility(View.GONE);
        }
    }
}
