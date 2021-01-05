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
                title_dsmonan.setTitle("Danh sách a");
                callData(idType);
                break;
            case 2:
                title_dsmonan.setTitle("Danh sách b");
                callData(idType);
                break;
            case 3:
                title_dsmonan.setTitle("Danh sách c");
                callData(idType);
                break;
            case 4:
                title_dsmonan.setTitle("Danh sách d");
                callData(idType);
                break;
            case 5:
                title_dsmonan.setTitle("Danh sách e");
                callData(idType);
                break;
            case 6:
                title_dsmonan.setTitle("Danh sách f");
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
                if (response.isSuccessful() && response.body() != null) {
                    foodsList = response.body();
                    for(Foods foods: foodsList){
                        Log.d("TAG", foods.getImg());
                    }
                    dSmonanAdapter = new DSmonanAdapter(context, foodsList);
                    recyclerView_dsmonan.setAdapter(dSmonanAdapter);
                    dSmonanAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {
            }
        });
//        updateAdapter();
    }

    public void updateAdapter(){
        dSmonanAdapter.notifyDataSetChanged();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }
}
