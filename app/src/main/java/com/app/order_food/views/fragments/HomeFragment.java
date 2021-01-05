package com.app.order_food.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;
import com.app.order_food.components.recyclerview.adapter.DSmonanThinhHanhAdapter;
import com.app.order_food.views.activities.main.MenuFoodActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    Integer idType;
    LinearLayout btna,btnb,btnc,btnd,btne,btnf;
    RecyclerView recyclerView_dsmonanthinhhanh, recyclerView_dstatcamonan;
    RetrofitClient retrofit = new RetrofitClient();
    List<Foods> foodsList = new ArrayList<>();
    Api api = retrofit.getClient().create(Api.class);
    DSmonanThinhHanhAdapter dSmonanThinhHanhAdapter;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btna = view.findViewById(R.id.btn_buaSang);
        btnb = view.findViewById(R.id.btn_buaTrua);
        btnc = view.findViewById(R.id.btn_buaChieu);
        btnd = view.findViewById(R.id.btn_banhmi);
        btne = view.findViewById(R.id.btn_mipho);
        btnf = view.findViewById(R.id.btn_xienque);
        recyclerView_dsmonanthinhhanh = view.findViewById(R.id.recyclerview_dsmonanthinhhanh);
        recyclerView_dstatcamonan = view.findViewById(R.id.recyclerview_dstatcamonan);
        foodsList = new ArrayList<>();
        api.getAllFood().enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    foodsList = response.body();
                    dSmonanThinhHanhAdapter = new DSmonanThinhHanhAdapter(context, foodsList);
                    recyclerView_dsmonanthinhhanh.setAdapter(dSmonanThinhHanhAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {

            }
        });
        init();
        return view;
    }

    public void init(){
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 1;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 2;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 3;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 4;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 5;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 6;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });



    }
}