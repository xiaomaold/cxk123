package com.example.fooddelivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    private List<MenuItem> menuItems;
    private OnAddToCartListener listener;

    public interface OnAddToCartListener {
        void onAddToCart(MenuItem menuItem);
        void onRemoveFromCart(MenuItem menuItem);
    }

    public MenuItemAdapter(List<MenuItem> menuItems, OnAddToCartListener listener) {
        this.menuItems = menuItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.bind(menuItem, listener);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public void updateQuantity(MenuItem menuItem, int quantity) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getId() == menuItem.getId()) {
                notifyItemChanged(i);
                return;
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMenuImage;
        TextView tvMenuName;
        TextView tvMenuDesc;
        TextView tvMenuPrice;
        Button btnAdd;
        Button btnRemove;
        TextView tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMenuImage = itemView.findViewById(R.id.iv_menu_image);
            tvMenuName = itemView.findViewById(R.id.tv_menu_name);
            tvMenuDesc = itemView.findViewById(R.id.tv_menu_desc);
            tvMenuPrice = itemView.findViewById(R.id.tv_menu_price);
            btnAdd = itemView.findViewById(R.id.btn_add);
            btnRemove = itemView.findViewById(R.id.btn_remove);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }

        public void bind(MenuItem menuItem, OnAddToCartListener listener) {
            ivMenuImage.setImageResource(menuItem.getImageResId());
            tvMenuName.setText(menuItem.getName());
            tvMenuDesc.setText(menuItem.getDescription());
            tvMenuPrice.setText(String.format("¥%.1f", menuItem.getPrice()));

            int quantity = CartManager.getInstance().getItemQuantity(menuItem);
            tvQuantity.setText(String.valueOf(quantity));

            btnAdd.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAddToCart(menuItem);
                }
            });

            btnRemove.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRemoveFromCart(menuItem);
                }
            });
        }
    }
}
