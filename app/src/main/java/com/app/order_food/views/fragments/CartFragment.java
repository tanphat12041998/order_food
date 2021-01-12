package com.app.order_food.views.fragments;

import android.content.Context;

import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.OrderFoods;
import com.app.order_food.components.Model.PaymentMethods;
import com.app.order_food.components.Model.Users;
import com.app.order_food.components.recyclerview.adapter.DsDHAdapter;
import com.app.order_food.views.activities.main.MainActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends BaseFragment {
    TextView text_gio_hang_trong, text_ten_nguoi_dung_don_hang, text_so_dien_thoai_don_hang, text_diachi_donhang, text_ghichu, text_thay_doi, text_tamtinhgiatien, text_tien_mat, text_tong_cong, text_ma_uu_dai;
    Button button_thanhtoan;
    ListView listview_dsdonhang;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Toolbar title_don_hang_cua_toi;
    DsDHAdapter dsDHAdapter;
    String ghi_chu;
    Integer tongtien = 0;
    Integer idpayment = 1;
    Integer iduser;
    Integer id = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initialVariables() {
        text_ten_nguoi_dung_don_hang = getView().findViewById(R.id.text_ten_nguoi_dung_don_hang);
        text_so_dien_thoai_don_hang = getView().findViewById(R.id.text_so_dien_thoai_don_hang);
        text_diachi_donhang = getView().findViewById(R.id.text_diachi_donhang);
        text_ghichu = getView().findViewById(R.id.text_ghichu);
//        text_thay_doi = getView().findViewById(R.id.text_thay_doi);
        text_tamtinhgiatien = getView().findViewById(R.id.text_tam_tinh_gia_tien);
        text_tien_mat = getView().findViewById(R.id.text_tien_mat);
        text_tong_cong = getView().findViewById(R.id.text_tong_cong);
//        text_ma_uu_dai = getView().findViewById(R.id.text_ma_uu_dai);
        button_thanhtoan = getView().findViewById(R.id.button_thanhtoan);
        listview_dsdonhang = getView().findViewById(R.id.listview_dsdonhang);
        text_gio_hang_trong = getView().findViewById(R.id.text_gio_hang_trong);
        title_don_hang_cua_toi = getView().findViewById(R.id.title_don_hang_cua_toi);
    }

    @Override
    protected void initialViewComponent() {

        dsDHAdapter = new DsDHAdapter(CartFragment.this, MainActivity.ListFoodDetail);
        listview_dsdonhang.setAdapter(dsDHAdapter);
        text_ten_nguoi_dung_don_hang.setText(MainActivity.Name);
        text_so_dien_thoai_don_hang.setText(MainActivity.Phone);
        text_diachi_donhang.setText(MainActivity.Address);
//        title_don_hang_cua_toi.setNavigationIcon(R.drawable.ic_close);
//
//        title_don_hang_cua_toi.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//            }
//        });
        if(MainActivity.ListFoodDetail.size() <=0){
            text_gio_hang_trong.setText("Giỏ hàng trống");
            text_gio_hang_trong.setVisibility(View.VISIBLE);
            listview_dsdonhang.setVisibility(View.INVISIBLE);
        }else {
            text_gio_hang_trong.setVisibility(View.INVISIBLE);
            listview_dsdonhang.setVisibility(View.VISIBLE);
        }
        tonggia();
        button_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = false;
                iduser = MainActivity.ID;
                ghi_chu = text_ghichu.getText().toString().trim();
                Calendar cal = Calendar.getInstance(Locale.getDefault());
                String dateTime = DateFormat.format("dd/MM/yyyy hh:mm:ss", cal).toString();

                for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
                    api.addOrderFood(id, iduser, idpayment, MainActivity.ListFoodDetail.get(i).getId(), dateTime
                            , MainActivity.ListFoodDetail.get(i).getGiatong()
                            , MainActivity.ListFoodDetail.get(i).getSl(),ghi_chu, status)
                            .enqueue(new Callback<OrderFoods>() {
                                @Override
                                public void onResponse(Call<OrderFoods> call, Response<OrderFoods> response) {

                                }

                                @Override
                                public void onFailure(Call<OrderFoods> call, Throwable t) {

                                }
                            });
                }
                id =  id + 1;
                MainActivity.ListFoodDetail.clear();
                text_ghichu.setText("");
                updated();
            }
        });
        updated();
    }


    public void tonggia() {
        if (MainActivity.ListFoodDetail.size() <= 0) {
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
        tonggia();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("id", id);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        id = sharedPref.getInt("id",id);


    }
}