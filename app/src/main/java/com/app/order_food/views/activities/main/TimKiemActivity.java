package com.app.order_food.views.activities.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.TypeFoods;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;
import com.app.order_food.views.activities.BaseActivity;
import com.app.order_food.views.activities.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemActivity extends BaseActivity {
    TextView text_huy_tim_kiem, text_ok;
    RecyclerView recyclerview_show;
    DSmonanAdapter dSmonanAdapter;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    List<TypeFoods> typeFoodsList = new ArrayList<>();
    List<Foods> foodsList = new ArrayList<>();
    List<Foods> foodsLists = new ArrayList<>();
    Context context;
    EditText text_thanh_tim_kiem;

    @Override
    protected void initialViewComponent() {
        text_huy_tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                onBackPressed();
            }
        });
        text_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "";
                a = text_thanh_tim_kiem.getText().toString();
                searchFoods(a);
            }
        });
    }

    @Override
    protected void initialVariables() {
        text_huy_tim_kiem = findViewById(R.id.text_huy_tim_kiem);
        recyclerview_show = findViewById(R.id.recyclerview_show);
        text_ok = findViewById(R.id.text_ok);
        text_thanh_tim_kiem =findViewById(R.id.text_thanh_tim_kiem);
        typeFoodsList = new ArrayList<>();
        foodsLists = new ArrayList<>();
        foodsList = new ArrayList<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
    private void searchFoods(final String s){
        api.getAllFood().enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                foodsList.clear();
                foodsLists.clear();
                foodsList = response.body();
                for (Foods foods : foodsList) {
                    if(foods.getName().toLowerCase().contains(s.toLowerCase())){
                        foodsLists.add(foods);
                        dSmonanAdapter = new DSmonanAdapter(context, foodsLists);
                        recyclerview_show.setAdapter(dSmonanAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {

            }
        });
    }
}
