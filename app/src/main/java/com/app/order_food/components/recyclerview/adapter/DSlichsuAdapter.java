package com.app.order_food.components.recyclerview.adapter;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.views.fragments.AccountFragment;
import com.app.order_food.views.fragments.LichSuFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSlichsuAdapter extends RecyclerView.Adapter<DSlichsuAdapter.RecyclerViewHolder> {
    ListView recyclerview_lichsuchitiet;
    TextView text_tamtinhgiatien, text_tongconggiatien, text_tennguoidat, text_addressnguoidat, text_phonenguoidat;
    List<OrderFoods> orderFoodsLists = new ArrayList<>();
    List<OrderFoods> orderFoodsList;
    public static List<OrderFoods> ListOrderNameFood;
    Context context;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Integer a = 0;
    Integer tongtien;
    ImageView imageView;
    LichSuChiTietAdapter lichSuChiTietAdapter;
    public DSlichsuAdapter(Context context, List<OrderFoods> orderFoodsList) {
        this.context = context;
        this.orderFoodsList = orderFoodsList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView text_ten_don_hang_lich_su, text_gia_tien_lich_su, text_da_giao, text_thoi_gian;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_ten_don_hang_lich_su = itemView.findViewById(R.id.text_ten_don_hang_lich_su);
            text_gia_tien_lich_su = itemView.findViewById(R.id.text_gia_tien_lich_su);
            text_da_giao = itemView.findViewById(R.id.text_da_giao);
            text_thoi_gian = itemView.findViewById(R.id.text_thoi_gian);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final OrderFoods orderFoods = orderFoodsList.get(pos);
                        final Dialog dialog = new Dialog(itemView.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.fragment_dat_lai);
                        recyclerview_lichsuchitiet = dialog.findViewById(R.id.listview_lichsuchitiet);
                        text_tamtinhgiatien = dialog.findViewById(R.id.text_tamtinhgiatien);
                        text_tongconggiatien = dialog.findViewById(R.id.text_tongconggiatien);
                        text_tennguoidat = dialog.findViewById(R.id.text_tennguoidat);
                        text_addressnguoidat = dialog.findViewById(R.id.text_addressnguoidat);
                        text_phonenguoidat = dialog.findViewById(R.id.text_phonenguoidat);
                        api.getAllOrderFoodById(orderFoods.getId()).enqueue(new Callback<List<OrderFoods>>() {
                            @Override
                            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                                orderFoodsLists.clear();
                                orderFoodsLists = response.body();
                                ListOrderNameFood = response.body();
//                                Log.d("TAG", orderFoodsLists.get(pos).getNameFood());
                                tongtien = 0;
                                for (int i = 0; i < orderFoodsLists.size(); i++) {

                                    tongtien += orderFoodsLists.get(i).getTotal();
                                    text_tamtinhgiatien.setText(tongtien + " VNĐ");
                                    text_tongconggiatien.setText(tongtien + " VNĐ");
                                }
                                lichSuChiTietAdapter = new LichSuChiTietAdapter(LichSuFragment.newInstance(), orderFoodsLists);
                                recyclerview_lichsuchitiet.setAdapter(lichSuChiTietAdapter);
                            }

                            @Override
                            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {

                            }
                        });
                        text_tennguoidat.setText(orderFoods.getName());
                        text_phonenguoidat.setText(orderFoods.getPhone());
                        text_addressnguoidat.setText(orderFoods.getAddress());


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
        holder.text_thoi_gian.setText(orderFoods.getDate());
        if(orderFoods.getStatus().equals(a)){
            holder.text_da_giao.setText("Đang giao hàng");
//            imageView.setImageResource(R.drawable.check1);
        }else {
            holder.text_da_giao.setText("Đã giao hàng");
//            imageView.setImageResource(R.drawable.check);
        }
    }

    @Override
    public int getItemCount() {
        return orderFoodsList.size();
    }

}
