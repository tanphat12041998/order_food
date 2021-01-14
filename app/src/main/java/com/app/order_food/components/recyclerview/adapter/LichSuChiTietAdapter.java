package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoodDetails;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.views.fragments.HomeFragment;
import com.app.order_food.views.fragments.LichSuFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LichSuChiTietAdapter extends BaseAdapter {
    LichSuFragment context;
    List<OrderFoods> orderFoodsList;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    String name, mo_ta;
    public LichSuChiTietAdapter(LichSuFragment context, List<OrderFoods> orderFoodsList) {
        this.context = context;
        this.orderFoodsList = orderFoodsList;
    }
    @Override
    public int getCount() {
        return orderFoodsList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderFoodsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder {
        public TextView text_so_luong_, text_ten_mon_an_, text_gia_tien_;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LichSuChiTietAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new LichSuChiTietAdapter.ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lich_su_chi_tiet, viewGroup, false);

            viewHolder.text_ten_mon_an_ = (TextView) view.findViewById(R.id.text_ten_mon_an_);
            viewHolder.text_so_luong_ = (TextView) view.findViewById(R.id.text_so_luong_);
            viewHolder.text_gia_tien_ = (TextView) view.findViewById(R.id.text_gia_tien_);
            view.setTag(viewHolder);
        }else {
            viewHolder = (LichSuChiTietAdapter.ViewHolder) view.getTag();
        }
        OrderFoods orderFoods = orderFoodsList.get(i);
        for (int k = 0; k < DSlichsuAdapter.ListOrderNameFood.size(); k++){
            viewHolder.text_ten_mon_an_.setText(DSlichsuAdapter.ListOrderNameFood.get(k).getNameFood());
        }
//        api.getAllOrderFoodByIdID(orderFoods.getId()).enqueue(new Callback<OrderFoods>() {
//            @Override
//            public void onResponse(Call<OrderFoods> call, Response<OrderFoods> response) {
//                name = response.body().getNameFood();
//            }
//
//            @Override
//            public void onFailure(Call<OrderFoods> call, Throwable t) {
//
//            }
//        });

        viewHolder.text_so_luong_.setText(orderFoods.getQuantity() +"x");
        viewHolder.text_gia_tien_.setText(orderFoods.getTotal() +" VNĐ");
        return view;
    }
}