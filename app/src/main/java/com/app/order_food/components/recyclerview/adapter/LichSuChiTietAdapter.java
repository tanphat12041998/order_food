package com.app.order_food.components.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
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
import com.app.order_food.views.fragments.MenuFoodFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LichSuChiTietAdapter extends BaseAdapter {
    LichSuFragment context;
    List<OrderFoods> orderFoodsList;
    List<OrderFoods> orderFoodsLists = new ArrayList<>();
    List<OrderFoods> orderFoodsListss = new ArrayList<>();
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    String name;

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
        if (view == null) {
            viewHolder = new LichSuChiTietAdapter.ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lich_su_chi_tiet, viewGroup, false);

            viewHolder.text_ten_mon_an_ = (TextView) view.findViewById(R.id.text_ten_mon_an_);
            viewHolder.text_so_luong_ = (TextView) view.findViewById(R.id.text_so_luong_);
            viewHolder.text_gia_tien_ = (TextView) view.findViewById(R.id.text_gia_tien_);
            view.setTag(viewHolder);
        } else {
            viewHolder = (LichSuChiTietAdapter.ViewHolder) view.getTag();
        }
        OrderFoods orderFoods = orderFoodsList.get(i);
//        for (int k = 0; k < DSlichsuAdapter.ListOrderNameFood.size(); k++){
//            viewHolder.text_ten_mon_an_.setText(DSlichsuAdapter.ListOrderNameFood.get(k).getNameFood());
//        }
        orderFoodsLists = new ArrayList<>();
        orderFoodsListss = new ArrayList<>();
//        api.getAllOrderFoodByIdID(orderFoods.getId()).enqueue(new Callback<List<OrderFoods>>() {
//            @Override
//            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
//                orderFoodsLists = response.body();
//                if (orderFoodsLists != null) {
//                    for (int k = 0; k < orderFoodsLists.size(); k++) {
//                        if(orderFoodsLists.get(k).getId().equals(orderFoods.getId())){
//                            name = orderFoodsLists.get(k).getNamefood();
//                        }
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
//
//            }
//        });


        ViewHolder finalViewHolder = viewHolder;
        api.getAllOrderFood().enqueue(new Callback<List<OrderFoods>>() {
            @Override
            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                orderFoodsLists.clear();
                orderFoodsLists = response.body();
                for (int k = 0; k < orderFoodsLists.size(); k++) {
                    if ((orderFoods.getId()).equals(orderFoodsLists.get(k).getId())) {
                        api.getAllOrderFoodByIdID(orderFoods.getId()).enqueue(new Callback<List<OrderFoods>>() {
                            @Override
                            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                                orderFoodsListss.clear();
                                orderFoodsListss = response.body();
                                for (int l = 0; l < orderFoodsListss.size(); l++) {
                                    finalViewHolder.text_ten_mon_an_.setText(orderFoodsListss.get(l).getNamefood() + "");
                                }

                            }
                            @Override
                            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
            }
        });


        viewHolder.text_so_luong_.setText(orderFoods.getQuantity() + "x");
        viewHolder.text_gia_tien_.setText(orderFoods.getTotal() + " VNĐ");
        return view;
    }
}