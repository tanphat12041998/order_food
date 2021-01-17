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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LichSuChiTietAdapter extends BaseAdapter {
    LichSuFragment context;
    List<OrderFoods> orderFoodsList;
    List<OrderFoods> orderFoodsLists = new ArrayList<>();
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
        orderFoodsLists = new ArrayList<>();
        ViewHolder finalViewHolder = viewHolder;
        DecimalFormat decimalFor= new DecimalFormat("##,###,###");
        api.getAllOrderFood().enqueue(new Callback<List<OrderFoods>>() {
            @Override
            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                orderFoodsLists.clear();
                orderFoodsLists = response.body();
                for (int k = 0; k < orderFoodsLists.size(); k++) {
                    if ((orderFoods.getNamefood()).equals(orderFoodsLists.get(k).getNamefood())) {
                        finalViewHolder.text_ten_mon_an_.setText(orderFoods.getNamefood() + "");
                    }
                }
            }
            @Override
            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
            }
        });
        viewHolder.text_so_luong_.setText(orderFoods.getQuantity() + "x");
        viewHolder.text_gia_tien_.setText(decimalFor.format(orderFoods.getTotal()) + " VNĐ");
        return view;
    }
}