package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;

import java.util.List;

public class DSmonanThinhHanhAdapter extends RecyclerView.Adapter<DSmonanThinhHanhAdapter.RecyclerViewHolder>{
    Context context;
    List<Foods> foodsList;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_dsmonanthinhhanh;
        TextView nameFood, price, rating;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView_dsmonanthinhhanh = itemView.findViewById(R.id.imageView_food);
            nameFood = itemView.findViewById(R.id.textView_name);
            price = itemView.findViewById(R.id.textView_price);
//            rating = itemView.findViewById(R.id.textView_like);
        }
    }

    public DSmonanThinhHanhAdapter(Context context, List<Foods> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    @NonNull
    @Override
    public DSmonanThinhHanhAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monan_home, parent, false);
        return new DSmonanThinhHanhAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSmonanThinhHanhAdapter.RecyclerViewHolder holder, int position) {
        final Foods foods = this.foodsList.get(position);
        holder.nameFood.setText("Tên: " + foods.getName());
        holder.price.setText("Giá: " + foods.getPrice() + " VND");
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }


}
