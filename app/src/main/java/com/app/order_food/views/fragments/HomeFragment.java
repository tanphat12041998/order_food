package com.app.order_food.views.fragments;

import android.content.Context;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;
import com.app.order_food.components.recyclerview.adapter.DSmonanThinhHanhAdapter;
import com.app.order_food.views.activities.main.MainActivity;
import com.app.order_food.views.activities.main.TimKiemActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {
    Integer idType;
    LinearLayout btna,btnb,btnc,btnd,btne,btnf;
    RecyclerView recyclerView_dsmonanthinhhanh, recyclerView_dstatcamonan;
    RetrofitClient retrofit = new RetrofitClient();
    List<Foods> foodsList = new ArrayList<>();
    List<Foods> foodsLists = new ArrayList<>();
    List<OrderFoods> orderFoodsList = new ArrayList<>();
    public static List<Foods> foods;
    Api api = retrofit.getClient().create(Api.class);
    DSmonanThinhHanhAdapter dSmonanThinhHanhAdapter;
    DSmonanAdapter dSmonanAdapter;
    Context context;
    Context contexts;
    TextView text_thanh_tim_kiem, dia_chi_home;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initialVariables() {
        btna = getView().findViewById(R.id.btn_com_Phan);
        btnb = getView().findViewById(R.id.btn_mon_Han);
        btnc = getView().findViewById(R.id.btn_Pizza);
        btnd = getView().findViewById(R.id.btn_thuc_An_Nhanh);
        btne = getView().findViewById(R.id.btn_mi_Pho);
        btnf = getView().findViewById(R.id.btn_do_Nuong);
        dia_chi_home = getView().findViewById(R.id.dia_chi_home);
        text_thanh_tim_kiem = getView().findViewById(R.id.text_thanh_tim_kiem);
        recyclerView_dsmonanthinhhanh = getView().findViewById(R.id.recyclerview_dsmonanthinhhanh);
        recyclerView_dstatcamonan = getView().findViewById(R.id.recyclerview_dstatcamonan);
        foodsList = new ArrayList<>();
        orderFoodsList= new ArrayList<>();
        foodsLists = new ArrayList<>();
    }

    @Override
    protected void initialViewComponent() {
        api.getAllFood().enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                foodsList.clear();
                if (response.isSuccessful() && response.body() != null) {
                    foodsList = response.body();
                    api.getAllOrderFoodNameFood().enqueue(new Callback<List<OrderFoods>>() {
                        @Override
                        public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
                            orderFoodsList.clear();
                            orderFoodsList = response.body();
                            if(orderFoodsList != null){
                                for (int a = 0; a < 42; a++){
                                    for(OrderFoods orderFoods: orderFoodsList){
                                        if(orderFoods.getNamefood().equals(foodsList.get(a).getName()) && orderFoods.getQuantity() >= 5){
                                            foodsLists.add(foodsList.get(a));
                                        }
                                    }
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
                        }
                    });

                    dSmonanThinhHanhAdapter = new DSmonanThinhHanhAdapter(context, foodsLists);
                    recyclerView_dsmonanthinhhanh.setAdapter(dSmonanThinhHanhAdapter);
                    dSmonanThinhHanhAdapter.notifyDataSetChanged();
                    foods = response.body();
                    dSmonanAdapter = new DSmonanAdapter(contexts, foodsList);
                    recyclerView_dstatcamonan.setAdapter(dSmonanAdapter);
                    dSmonanAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {

            }
        });
        dia_chi_home.setText(MainActivity.Address);
        init();
    }


    public void init(){
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 1;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, MenuFoodFragment.newInstance(idType));
                fragmentTransaction.addToBackStack(MenuFoodFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });
        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 2;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, MenuFoodFragment.newInstance(idType));
                fragmentTransaction.addToBackStack(MenuFoodFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 3;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, MenuFoodFragment.newInstance(idType));
                fragmentTransaction.addToBackStack(MenuFoodFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }

        });
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 4;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, MenuFoodFragment.newInstance(idType));
                fragmentTransaction.addToBackStack(MenuFoodFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });
        btne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 5;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, MenuFoodFragment.newInstance(idType));
                fragmentTransaction.addToBackStack(MenuFoodFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 6;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, MenuFoodFragment.newInstance(idType));
                fragmentTransaction.addToBackStack(MenuFoodFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });
        text_thanh_tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimKiemActivity.class);
                startActivity(intent);
            }
        });
    }
}