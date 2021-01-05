package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;

import java.util.List;

public class DSmonanAdapter extends RecyclerView.Adapter<DSmonanAdapter.RecyclerViewHolder> {
    Context context;
    List<Foods> foodsList;

    public DSmonanAdapter(Context context, List<Foods> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_dsmonan;
        TextView titleFood, description, price, rating;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView_dsmonan = itemView.findViewById(R.id.recyclerview_dsmonan);
            titleFood = itemView.findViewById(R.id.titleFood);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
        }
    }


    @NonNull
    @Override
    public DSmonanAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monan_chitiet, parent, false);
        return new DSmonanAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSmonanAdapter.RecyclerViewHolder holder, int position) {
        final Foods foods = this.foodsList.get(position);
        holder.titleFood.setText("Tên: " + foods.getName());
        holder.description.setText("Mô tả: " + foods.getDescription());
        holder.price.setText("Giá: " + foods.getPrice() + " VND");
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

}
