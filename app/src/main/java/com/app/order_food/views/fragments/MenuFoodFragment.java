package com.app.order_food.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;
import com.app.order_food.views.activities.main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public static FloatingActionButton btn_fab;
    public static String abc;
    @Override
    protected void initialVariables() {
        recyclerView_dsmonan = getView().findViewById(R.id.recyclerview_dsmonan);
        title_dsmonan = getView().findViewById(R.id.title_dsmonan);
        btn_fab = getView().findViewById(R.id.btn_fab);
        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
        Bundle bundle = getArguments();
        a = bundle.getInt(ID, 0);


    }

    @Override
    protected void initialViewComponent() {
        switch (a){
            case 1:
                title_dsmonan.setTitle("Cơm phần");
                callData(a);
                break;
            case 2:
                title_dsmonan.setTitle("Món Hàn");
                callData(a);
                break;
            case 3:
                title_dsmonan.setTitle("Món Pizza");
                callData(a);
                break;
            case 4:
                title_dsmonan.setTitle("Món ăn nhanh");
                callData(a);
                break;
            case 5:
                title_dsmonan.setTitle("Món ăn Mì-Phở");
                callData(a);
                break;
            case 6:
                title_dsmonan.setTitle("Món ăn Nướng");
                callData(a);
                break;
            default:
                break;
        }

        title_dsmonan.setNavigationIcon(R.drawable.ic_back_white);
        title_dsmonan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });
        abc = "";
        if(MainActivity.ListFoodDetail.size() <= 0){
            btn_fab.setVisibility(View.VISIBLE);
        }else {
            btn_fab.setVisibility(View.VISIBLE);
            btn_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abc = "0";
                    MainActivity.button_sheet.setVisibility(View.INVISIBLE);
                    FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                    fragmentTransaction.add(R.id.main, CartFragment.newInstance());
                    fragmentTransaction.addToBackStack(CartFragment.class.getSimpleName());
                    fragmentTransaction.commit();
                }
            });
        }
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
