package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.R;
import com.app.order_food.components.Model.OrderFoods;

import java.util.List;

public class DSlichsuAdapter extends RecyclerView.Adapter<DSlichsuAdapter.RecyclerViewHolder>{
    Context context;
    List<OrderFoods> orderFoodsList;

    @NonNull
    @Override
    public DSlichsuAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new DSlichsuAdapter.RecyclerViewHolder(view);
    }

    public DSlichsuAdapter(Context context, List<OrderFoods> orderFoodsList) {
        this.context = context;
        this.orderFoodsList = orderFoodsList;
    }

    @Override
    public void onBindViewHolder(@NonNull DSlichsuAdapter.RecyclerViewHolder holder, int position) {
        final OrderFoods orderFoods = this.orderFoodsList.get(position);
        holder.text_ten_don_hang_lich_su.setText("Mã đơn hàng số: #00" + orderFoods.getId());
        holder.text_gia_tien_lich_su.setText(orderFoods.getTotal() + " VNĐ");
        holder.text_dat_lai_lich_su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return orderFoodsList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView text_ten_don_hang_lich_su, text_gia_tien_lich_su, text_dat_lai_lich_su;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_ten_don_hang_lich_su = itemView.findViewById(R.id.text_ten_don_hang_lich_su);
            text_gia_tien_lich_su = itemView.findViewById(R.id.text_gia_tien_lich_su);
            text_dat_lai_lich_su = itemView.findViewById(R.id.text_dat_lai_lich_su);
        }
    }
}
