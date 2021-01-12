package com.app.order_food.views.fragments;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.Model.PaymentMethods;
import com.app.order_food.components.Model.Users;
import com.app.order_food.components.recyclerview.adapter.DsDHAdapter;
import com.app.order_food.views.activities.main.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends BaseFragment {
    TextView text_gio_hang_trong, text_ten_nguoi_dung_don_hang, text_so_dien_thoai_don_hang, text_diachi_donhang, text_ghichu, text_thay_doi, text_tamtinhgiatien, text_tien_mat, text_tong_cong, text_ma_uu_dai;
    Button button_thanhtoan;
    ListView listview_dsdonhang;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    List<Users> usersList = new ArrayList<>();
    Users user;
    DsDHAdapter dsDHAdapter;
    Integer tongtien = 0;
    Context context;
    Integer idpayment, iduser;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initialVariables() {
        text_ten_nguoi_dung_don_hang = getView().findViewById(R.id.text_ten_nguoi_dung_don_hang);
        text_so_dien_thoai_don_hang = getView().findViewById(R.id.text_so_dien_thoai_don_hang);
        text_diachi_donhang = getView().findViewById(R.id.text_diachi_donhang);
//        text_ghichu = getView().findViewById(R.id.text_ghichu);
//        text_thay_doi = getView().findViewById(R.id.text_thay_doi);
        text_tamtinhgiatien = getView().findViewById(R.id.text_tamtinhgiatien);
        text_tien_mat = getView().findViewById(R.id.text_tien_mat);
        text_tong_cong = getView().findViewById(R.id.text_tong_cong);
//        text_ma_uu_dai = getView().findViewById(R.id.text_ma_uu_dai);
        button_thanhtoan = getView().findViewById(R.id.button_thanhtoan);
        listview_dsdonhang = getView().findViewById(R.id.listview_dsdonhang);
        text_gio_hang_trong = getView().findViewById(R.id.text_gio_hang_trong);
    }

    @Override
    protected void initialViewComponent() {

        dsDHAdapter = new DsDHAdapter(CartFragment.this, MainActivity.ListFoodDetail);
        listview_dsdonhang.setAdapter(dsDHAdapter);
        text_ten_nguoi_dung_don_hang.setText(MainActivity.Name);
        text_so_dien_thoai_don_hang.setText(MainActivity.Phone);
        text_diachi_donhang.setText(MainActivity.Address);

        if(MainActivity.ListFoodDetail.size() <=0){
            text_gio_hang_trong.setText("Giỏ hàng trống");
            text_gio_hang_trong.setVisibility(View.VISIBLE);
            listview_dsdonhang.setVisibility(View.INVISIBLE);
        }else {
            text_gio_hang_trong.setVisibility(View.INVISIBLE);
            listview_dsdonhang.setVisibility(View.VISIBLE);
        }

        button_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = false;
                iduser = MainActivity.ID;
                Calendar cal = Calendar.getInstance(Locale.getDefault());
                String dateTime = DateFormat.format("dd/MM/yyyy hh:mm:ss", cal).toString();
                for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
                    api.addOrderFood(1, iduser, 1, MainActivity.ListFoodDetail.get(i).getId(), dateTime
                            , MainActivity.ListFoodDetail.get(i).getGiatong()
                            , MainActivity.ListFoodDetail.get(i).getSl(), status)
                            .enqueue(new Callback<OrderFoods>() {
                                @Override
                                public void onResponse(Call<OrderFoods> call, Response<OrderFoods> response) {

                                }

                                @Override
                                public void onFailure(Call<OrderFoods> call, Throwable t) {

                                }
                            });
                }
                MainActivity.ListFoodDetail.clear();
                updated();
            }
        });
        tonggia();
        updated();
    }


    public void tonggia() {
        if (MainActivity.ListFoodDetail == null) {
            tongtien = 0;
            text_tamtinhgiatien.setText(tongtien + " VNĐ");
            text_tien_mat.setText(tongtien + " VNĐ");
            text_tong_cong.setText(tongtien + " VNĐ");
        }else {
            tongtien = 0;
            for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
                tongtien += MainActivity.ListFoodDetail.get(i).getGiatong();
                text_tamtinhgiatien.setText(tongtien + " VNĐ");
                text_tien_mat.setText(tongtien + " VNĐ");
                text_tong_cong.setText(tongtien + " VNĐ");
            }
        }

    }

    public void updated() {
        dsDHAdapter.notifyDataSetChanged();
        for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
            tongtien += MainActivity.ListFoodDetail.get(i).getGiatong();
        }

    }


}