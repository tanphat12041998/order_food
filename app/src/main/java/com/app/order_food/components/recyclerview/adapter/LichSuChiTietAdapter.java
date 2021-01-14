package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LichSuChiTietAdapter extends RecyclerView.Adapter<LichSuChiTietAdapter.RecyclerViewHolder> {
    Context context;
    List<OrderFoods> orderFoodsList;
    List<OrderFoods> orderFoodss = new ArrayList<>();
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    public LichSuChiTietAdapter(Context context, List<OrderFoods> orderFoodsList) {
        this.context = context;
        this.orderFoodsList = orderFoodsList;
    }
    @NonNull
    @Override
    public LichSuChiTietAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_su_chi_tiet, parent, false);
        return new LichSuChiTietAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuChiTietAdapter.RecyclerViewHolder holder, int position) {
        final OrderFoods orderFoods = this.orderFoodsList.get(position);
        holder.text_ten_mon_an_.setText(orderFoods.getName());
        holder.text_mo_ta_.setText(orderFoods.getName());
        holder.text_so_luong_.setText(orderFoods.getQuantity()+"");
        holder.text_gia_tien_.setText(orderFoods.getTotal()+"");

    }

    @Override
    public int getItemCount() {
        return orderFoodsList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView text_so_luong_, text_ten_mon_an_, text_mo_ta_, text_gia_tien_;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_ten_mon_an_ = itemView.findViewById(R.id.text_ten_mon_an_);
            text_so_luong_ = itemView.findViewById(R.id.text_so_luong_);
            text_mo_ta_ = itemView.findViewById(R.id.text_mo_ta_);
            text_gia_tien_ = itemView.findViewById(R.id.text_gia_tien_);
            orderFoodss = new ArrayList<>();
        }
    }
}