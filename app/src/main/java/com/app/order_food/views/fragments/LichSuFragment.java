package com.app.order_food.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.recyclerview.adapter.DSlichsuAdapter;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;
import com.app.order_food.views.activities.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuFragment extends BaseFragment {
    Toolbar title_don_hang_cua_toi;
    RecyclerView recyclerview_lich_su;
    List<OrderFoods> orderFoodsList = new ArrayList<>();
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Context context;
    DSlichsuAdapter dSlichsuAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_don_hang;
    }

    @Override
    protected void initialVariables() {
        title_don_hang_cua_toi = getView().findViewById(R.id.title_don_hang_cua_toi);
        recyclerview_lich_su = getView().findViewById(R.id.recyclerview_lich_su);
        orderFoodsList = new ArrayList<>();
    }

    @Override
    protected void initialViewComponent() {
        api.getOrderFoodById(MainActivity.ID).enqueue(new Callback<List<OrderFoods>>() {
            @Override
            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                orderFoodsList.clear();
                if (response.isSuccessful() && response.body() != null) {
                    orderFoodsList = response.body();
                    dSlichsuAdapter = new DSlichsuAdapter(context, orderFoodsList);
                    recyclerview_lich_su.setAdapter(dSlichsuAdapter);
                }
                dSlichsuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {

            }
        });
        title_don_hang_cua_toi.setNavigationIcon(R.drawable.ic_back_white);
        title_don_hang_cua_toi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
    public static LichSuFragment newInstance() {
        Bundle args = new Bundle();
        LichSuFragment fragment = new LichSuFragment();
        fragment.setArguments(args);
        return fragment;
    }
}