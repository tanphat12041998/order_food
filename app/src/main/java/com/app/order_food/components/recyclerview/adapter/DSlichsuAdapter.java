package com.app.order_food.components.recyclerview.adapter;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.OrderFoods;

import java.util.ArrayList;
import java.util.List;

public class DSlichsuAdapter extends RecyclerView.Adapter<DSlichsuAdapter.RecyclerViewHolder> {
    RecyclerView recyclerview_lichsuchitiet;
    TextView text_tamtinhgiatien, text_tongconggiatien, text_tennguoidat, text_addressnguoidat, text_phonenguoidat;
    List<OrderFoods> orderFoodsLists = new ArrayList<>();
    List<OrderFoods> orderFoodsList;
    Context context;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    LichSuChiTietAdapter lichSuChiTietAdapter;

    public DSlichsuAdapter(Context context, List<OrderFoods> orderFoodsList) {
        this.context = context;
        this.orderFoodsList = orderFoodsList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView text_ten_don_hang_lich_su, text_gia_tien_lich_su;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_ten_don_hang_lich_su = itemView.findViewById(R.id.text_ten_don_hang_lich_su);
            text_gia_tien_lich_su = itemView.findViewById(R.id.text_gia_tien_lich_su);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final OrderFoods orderFoods = orderFoodsList.get(pos);
                        final Dialog dialog = new Dialog(itemView.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.fragment_dat_lai);
                        recyclerview_lichsuchitiet = dialog.findViewById(R.id.recyclerview_lichsuchitiet);
                        text_tamtinhgiatien = dialog.findViewById(R.id.text_tamtinhgiatien);
                        text_tongconggiatien = dialog.findViewById(R.id.text_tongconggiatien);
                        text_tennguoidat = dialog.findViewById(R.id.text_tennguoidat);
                        text_addressnguoidat = dialog.findViewById(R.id.text_addressnguoidat);
                        text_phonenguoidat = dialog.findViewById(R.id.text_phonenguoidat);
                        text_tennguoidat.setText("Tên: " +orderFoods.getName());
                        text_phonenguoidat.setText("SĐT: " +orderFoods.getPhone());
                        text_addressnguoidat.setText("Địa chỉ: " +orderFoods.getAddress());

                        dialog.show();
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public DSlichsuAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new DSlichsuAdapter.RecyclerViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DSlichsuAdapter.RecyclerViewHolder holder, int position) {
        final OrderFoods orderFoods = this.orderFoodsList.get(position);
        holder.text_ten_don_hang_lich_su.setText("Mã đơn hàng số: #00" + orderFoods.getId());
        holder.text_gia_tien_lich_su.setText(orderFoods.getTotal() + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return orderFoodsList.size();
    }


}
