package com.example.fooddelivery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCart;
    private CartAdapter adapter;
    private TextView tvEmpty;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private Button btnClearCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        setupRecyclerView();
    }

    private void initViews() {
        rvCart = findViewById(R.id.rv_cart);
        tvEmpty = findViewById(R.id.tv_empty);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnClearCart = findViewById(R.id.btn_clear_cart);

        btnCheckout.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            finish();
        });

        btnClearCart.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            finish();
        });
    }

    private void setupRecyclerView() {
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();
        adapter = new CartAdapter(cartItems);

        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(adapter);

        updateUI();
    }

    private void updateUI() {
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        if (cartItems.isEmpty()) {
            rvCart.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
            btnCheckout.setVisibility(View.GONE);
            btnClearCart.setVisibility(View.GONE);
            tvTotalPrice.setVisibility(View.GONE);
        } else {
            rvCart.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.VISIBLE);
            btnClearCart.setVisibility(View.VISIBLE);
            tvTotalPrice.setVisibility(View.VISIBLE);
            tvTotalPrice.setText(String.format("总计: ¥%.1f", CartManager.getInstance().getTotalPrice()));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
}
