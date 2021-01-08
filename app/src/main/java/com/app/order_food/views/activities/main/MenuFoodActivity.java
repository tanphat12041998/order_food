package com.app.order_food.views.activities.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.Users;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;
import com.app.order_food.views.activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFoodActivity extends BaseActivity {
    Toolbar title_dsmonan;
    List<Foods> foodsList = new ArrayList<>();
    RecyclerView recyclerView_dsmonan;
    DSmonanAdapter dSmonanAdapter;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Context context;
    Integer idType;
    Foods foods;
    @Override
    protected void initialViewComponent() {
        Intent intent = getIntent();
        idType = intent.getIntExtra("idType",0);
        switch (idType){
            case 1:
                title_dsmonan.setTitle("Danh sách Cơm Phần");
                callData(idType);
                break;
            case 2:
                title_dsmonan.setTitle("Danh sách món Hàn");
                callData(idType);
                break;
            case 3:
                title_dsmonan.setTitle("Danh sách món Pizza");
                callData(idType);
                break;
            case 4:
                title_dsmonan.setTitle("Danh sách món ăn nhanh");
                callData(idType);
                break;
            case 5:
                title_dsmonan.setTitle("Danh sách món ăn Mì-Phở");
                callData(idType);
                break;
            case 6:
                title_dsmonan.setTitle("Danh sách món ăn Nướng");
                callData(idType);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initialVariables() {
        recyclerView_dsmonan = findViewById(R.id.recyclerview_dsmonan);
        title_dsmonan = findViewById(R.id.title_dsmonan);
    }

    public void callData(Integer idTyped){
        api.getAllFoodByIdType(idTyped).enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                foodsList.clear();
                if (response.isSuccessful() && response.body() != null) {
                    foodsList = response.body();
//                    for(Foods foods: foodsList){
//                        Log.d("TAG", foods.getImg());
//                    }
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


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }
}
