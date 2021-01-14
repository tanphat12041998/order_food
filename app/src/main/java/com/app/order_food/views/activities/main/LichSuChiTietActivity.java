//package com.app.order_food.views.activities.main;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.app.order_food.API.Api;
//import com.app.order_food.API.RetrofitClient;
//import com.app.order_food.R;
//import com.app.order_food.components.Model.OrderFoods;
//import com.app.order_food.components.recyclerview.adapter.LichSuChiTietAdapter;
//import com.app.order_food.views.activities.BaseActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class LichSuChiTietActivity extends BaseActivity {
//    public static Integer idOrder;
//    Toolbar title_toolbar;
//    RecyclerView recyclerview_lichsuchitiet;
//    TextView text_tamtinhgiatien, text_tongconggiatien, text_tennguoidat, text_addressnguoidat, text_phonenguoidat;
//    List<OrderFoods> orderFoodsLists = new ArrayList<>();
//    List<OrderFoods> orderFoodsListss = new ArrayList<>();
//    Context context;
//    LichSuChiTietAdapter lichSuChiTietAdapter;
//    Intent intent;
//    RetrofitClient retrofit = new RetrofitClient();
//    Api api = retrofit.getClient().create(Api.class);
//    Integer tongtien;
//    @Override
//    protected void initialViewComponent() {
//        Log.d("TAG", idOrder.toString());
//        api.getAllOrderFood().enqueue(new Callback<List<OrderFoods>>() {
//            @Override
//            public void onResponse(Call<List<OrderFoods>> call, Response<List<OrderFoods>> response) {
//                orderFoodsLists = response.body();
//                for (OrderFoods orderFoods : orderFoodsLists) {
//                    if (idOrder.equals(orderFoods.getId()) && (MainActivity.ID).equals(orderFoods.getIdUser())) {
//                        orderFoodsListss.add(orderFoods);
//                        Log.d("TAG", orderFoodsLists.toString());
//                        text_tennguoidat.setText(orderFoods.getName());
//                        text_phonenguoidat.setText(orderFoods.getPhone());
//                        text_addressnguoidat.setText(orderFoods.getAddress());
//                        lichSuChiTietAdapter = new LichSuChiTietAdapter(context, orderFoodsListss);
//                        recyclerview_lichsuchitiet.setAdapter(lichSuChiTietAdapter);
//
//
//                    }
//                }
//                lichSuChiTietAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<OrderFoods>> call, Throwable t) {
//
//            }
//        });
//        tongtien();
//        title_toolbar.setNavigationIcon(R.drawable.ic_close);
//        title_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }
//
//    @Override
//    protected void initialVariables() {
//        intent = getIntent();
//        idOrder = intent.getExtras().getInt("idorder");
//        recyclerview_lichsuchitiet = findViewById(R.id.recyclerview_lichsuchitiet);
//        text_tamtinhgiatien = findViewById(R.id.text_tamtinhgiatien);
//        text_tongconggiatien = findViewById(R.id.text_tongconggiatien);
//        text_tennguoidat = findViewById(R.id.text_tennguoidat);
//        text_addressnguoidat = findViewById(R.id.text_addressnguoidat);
//        text_phonenguoidat = findViewById(R.id.text_phonenguoidat);
//        orderFoodsLists = new ArrayList<>();
//        orderFoodsListss = new ArrayList<>();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_dat_lai;
//    }
//    public void tongtien(){
//        tongtien = 0;
//        for (int i = 0; i < orderFoodsListss.size(); i++) {
//            tongtien += orderFoodsListss.get(i).getTotal();
//            text_tamtinhgiatien.setText(tongtien + " VNĐ");
//            text_tongconggiatien.setText(tongtien + " VNĐ");
//        }
//    }
//}