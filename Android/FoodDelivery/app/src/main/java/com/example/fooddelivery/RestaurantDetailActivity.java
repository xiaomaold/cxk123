package com.example.fooddelivery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity {

    private ImageView ivRestaurant;
    private TextView tvName;
    private TextView tvRating;
    private TextView tvDeliveryTime;
    private TextView tvDeliveryFee;
    private RecyclerView rvMenu;
    private MenuItemAdapter adapter;
    private Button btnCart;
    private Restaurant restaurant;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

        initViews();
        initData();
        setupRecyclerView();
    }

    private void initViews() {
        ivRestaurant = findViewById(R.id.iv_restaurant);
        tvName = findViewById(R.id.tv_name);
        tvRating = findViewById(R.id.tv_rating);
        tvDeliveryTime = findViewById(R.id.tv_delivery_time);
        tvDeliveryFee = findViewById(R.id.tv_delivery_fee);
        rvMenu = findViewById(R.id.rv_menu);
        btnCart = findViewById(R.id.btn_cart);

        btnCart.setOnClickListener(v -> {
            finish();
        });

        if (restaurant != null) {
            ivRestaurant.setImageResource(restaurant.getImageResId());
            tvName.setText(restaurant.getName());
            tvRating.setText(String.valueOf(restaurant.getRating()));
            tvDeliveryTime.setText(restaurant.getDeliveryTime());
            tvDeliveryFee.setText(String.format("¥%.1f", restaurant.getDeliveryFee()));
        }
    }

    private void initData() {
        menuItems = new ArrayList<>();
        
        if (restaurant != null) {
            switch (restaurant.getId()) {
                case 1:
                    menuItems.add(new MenuItem(1, "麻婆豆腐", "经典川菜，麻辣鲜香", 28.0, 
                            R.drawable.food1, 1));
                    menuItems.add(new MenuItem(2, "宫保鸡丁", "酸甜微辣，鸡肉嫩滑", 32.0, 
                            R.drawable.food2, 1));
                    menuItems.add(new MenuItem(3, "水煮鱼", "鱼肉鲜嫩，麻辣过瘾", 58.0, 
                            R.drawable.food3, 1));
                    menuItems.add(new MenuItem(4, "回锅肉", "肥而不腻，香辣可口", 35.0, 
                            R.drawable.food4, 1));
                    break;
                case 2:
                    menuItems.add(new MenuItem(5, "叉烧饭", "蜜汁叉烧，香甜可口", 25.0, 
                            R.drawable.food5, 2));
                    menuItems.add(new MenuItem(6, "虾饺", "晶莹剔透，鲜虾馅料", 18.0, 
                            R.drawable.food6, 2));
                    menuItems.add(new MenuItem(7, "蛋挞", "酥脆外皮，嫩滑内馅", 12.0, 
                            R.drawable.food7, 2));
                    menuItems.add(new MenuItem(8, "云吞面", "鲜虾云吞，汤清面爽", 22.0, 
                            R.drawable.food8, 2));
                    break;
                case 3:
                    menuItems.add(new MenuItem(9, "猪肉白菜水饺", "皮薄馅大，鲜美多汁", 28.0, 
                            R.drawable.food9, 3));
                    menuItems.add(new MenuItem(10, "韭菜鸡蛋水饺", "清香爽口，营养丰富", 25.0, 
                            R.drawable.food10, 3));
                    menuItems.add(new MenuItem(11, "锅包肉", "外酥里嫩，酸甜可口", 38.0, 
                            R.drawable.food11, 3));
                    menuItems.add(new MenuItem(12, "地三鲜", "茄子土豆青椒，经典东北菜", 26.0, 
                            R.drawable.food12, 3));
                    break;
                case 4:
                    menuItems.add(new MenuItem(13, "三文鱼刺身", "新鲜三文鱼，肉质鲜美", 68.0, 
                            R.drawable.food13, 4));
                    menuItems.add(new MenuItem(14, "鳗鱼饭", "烤鳗鱼，酱汁浓郁", 58.0, 
                            R.drawable.food14, 4));
                    menuItems.add(new MenuItem(15, "天妇罗", "酥脆外衣，鲜嫩内馅", 48.0, 
                            R.drawable.food15, 4));
                    menuItems.add(new MenuItem(16, "味噌拉面", "浓郁味噌汤底，配料丰富", 42.0, 
                            R.drawable.food16, 4));
                    break;
                case 5:
                    menuItems.add(new MenuItem(17, "玛格丽特披萨", "经典番茄芝士披萨", 58.0, 
                            R.drawable.food17, 5));
                    menuItems.add(new MenuItem(18, "意式肉酱面", "浓郁肉酱，面条劲道", 38.0, 
                            R.drawable.food18, 5));
                    menuItems.add(new MenuItem(19, "凯撒沙拉", "新鲜蔬菜，凯撒酱汁", 32.0, 
                            R.drawable.food19, 5));
                    menuItems.add(new MenuItem(20, "提拉米苏", "经典意式甜点", 28.0, 
                            R.drawable.food20, 5));
                    break;
            }
        }
    }

    private void setupRecyclerView() {
        adapter = new MenuItemAdapter(menuItems, new MenuItemAdapter.OnAddToCartListener() {
            @Override
            public void onAddToCart(MenuItem menuItem) {
                CartManager.getInstance().addToCart(menuItem);
                adapter.updateQuantity(menuItem, CartManager.getInstance().getItemQuantity(menuItem));
                updateCartButton();
            }

            @Override
            public void onRemoveFromCart(MenuItem menuItem) {
                CartManager.getInstance().removeFromCart(menuItem);
                adapter.updateQuantity(menuItem, CartManager.getInstance().getItemQuantity(menuItem));
                updateCartButton();
            }
        });

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setAdapter(adapter);
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
            btnCart.setText(String.format("购物车 (%d) - ¥%.1f", 
                    totalQuantity, CartManager.getInstance().getTotalPrice()));
        } else {
            btnCart.setVisibility(View.GONE);
        }
    }
}
