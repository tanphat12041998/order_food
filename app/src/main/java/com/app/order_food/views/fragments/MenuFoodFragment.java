package com.app.order_food.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFoodFragment extends BaseFragment {
    private static String ID = "ID_TYPE";
    Toolbar title_dsmonan;
    List<Foods> foodsList = new ArrayList<>();
    RecyclerView recyclerView_dsmonan;
    DSmonanAdapter dSmonanAdapter;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Context context;
    Integer a;

    @Override
    protected void initialVariables() {
        recyclerView_dsmonan = getView().findViewById(R.id.recyclerview_dsmonan);
        title_dsmonan = getView().findViewById(R.id.title_dsmonan);
        Bundle bundle = getArguments();
        a = bundle.getInt(ID, 0);
//        Log.d("MenuFoodFragment", String.valueOf(a));


    }

    @Override
    protected void initialViewComponent() {
        switch (a){
            case 1:
                title_dsmonan.setTitle("Danh sách Cơm Phần");
                callData(a);
                break;
            case 2:
                title_dsmonan.setTitle("Danh sách món Hàn");
                callData(a);
                break;
            case 3:
                title_dsmonan.setTitle("Danh sách món Pizza");
                callData(a);
                break;
            case 4:
                title_dsmonan.setTitle("Danh sách món ăn nhanh");
                callData(a);
                break;
            case 5:
                title_dsmonan.setTitle("Danh sách món ăn Mì-Phở");
                callData(a);
                break;
            case 6:
                title_dsmonan.setTitle("Danh sách món ăn Nướng");
                callData(a);
                break;
            default:
                break;
        }

        title_dsmonan.setNavigationIcon(R.drawable.ic_back_white);
        title_dsmonan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    public static MenuFoodFragment newInstance(int id) {
        Bundle args = new Bundle();
        MenuFoodFragment fragment = new MenuFoodFragment();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }
    public void callData(Integer idTyped){
        api.getAllFoodByIdType(idTyped).enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                foodsList.clear();
                if (response.isSuccessful() && response.body() != null) {
                    foodsList = response.body();
                    dSmonanAdapter = new DSmonanAdapter(context, foodsList);
                    recyclerView_dsmonan.setAdapter(dSmonanAdapter);
                    updateAdapter();
                }
            }
            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {
            }
        });


    }
    private void updateAdapter() {
        dSmonanAdapter.notifyDataSetChanged();
    }
}
