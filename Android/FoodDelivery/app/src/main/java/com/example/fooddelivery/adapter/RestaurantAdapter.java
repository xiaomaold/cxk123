package com.example.fooddelivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> restaurantList;
    private OnRestaurantClickListener listener;

    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant);
    }

    public RestaurantAdapter(List<Restaurant> restaurantList, OnRestaurantClickListener listener) {
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.bind(restaurant, listener);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurant;
        TextView tvName;
        TextView tvCategory;
        TextView tvRating;
        TextView tvDeliveryTime;
        TextView tvDeliveryFee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRestaurant = itemView.findViewById(R.id.iv_restaurant);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvDeliveryTime = itemView.findViewById(R.id.tv_delivery_time);
            tvDeliveryFee = itemView.findViewById(R.id.tv_delivery_fee);
        }

        public void bind(Restaurant restaurant, OnRestaurantClickListener listener) {
            ivRestaurant.setImageResource(restaurant.getImageResId());
            tvName.setText(restaurant.getName());
            tvCategory.setText(restaurant.getCategory());
            tvRating.setText(String.valueOf(restaurant.getRating()));
            tvDeliveryTime.setText(restaurant.getDeliveryTime());
            tvDeliveryFee.setText(String.format("¥%.1f", restaurant.getDeliveryFee()));

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRestaurantClick(restaurant);
                }
            });
        }
    }
}
