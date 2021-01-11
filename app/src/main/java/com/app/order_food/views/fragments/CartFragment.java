package com.app.order_food.views.fragments;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Users;
import com.app.order_food.components.recyclerview.adapter.DsDHAdapter;
import com.app.order_food.views.activities.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment {
    TextView  text_ten_nguoi_dung_don_hang, text_so_dien_thoai_don_hang, text_diachi_donhang, text_ghichu, text_thay_doi, text_tamtinhgiatien, text_tien_mat, text_tong_cong, text_ma_uu_dai;
    Button button_thanhtoan;
    ListView listview_dsdonhang;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    List<Users> usersList = new ArrayList<>();
    Users user;
    DsDHAdapter dsDHAdapter;
    Double tongtien = 0.0;
    Context context;
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
        usersList = new ArrayList<>();

    }

    @Override
    protected void initialViewComponent() {
        dsDHAdapter = new DsDHAdapter(CartFragment.this, MainActivity.ListFoodDetail);
        listview_dsdonhang.setAdapter(dsDHAdapter);
        tonggia();
        dsDHAdapter.notifyDataSetChanged();
        text_ten_nguoi_dung_don_hang.setText(MainActivity.Name);
        text_so_dien_thoai_don_hang.setText(MainActivity.Phone);
        text_diachi_donhang.setText(MainActivity.Address);
        updated();
    }


    public void tonggia(){
        tongtien = 0.0;
        if(MainActivity.ListFoodDetail != null){
            tongtien = 0.0;
            for (int i =0; i < MainActivity.ListFoodDetail.size() ; i++){
                tongtien += MainActivity.ListFoodDetail.get(i).getGia();
            }
            text_tamtinhgiatien.setText(tongtien + " VNĐ");
            text_tien_mat.setText(tongtien + " VNĐ");
            text_tong_cong.setText(tongtien + " VNĐ");
            dsDHAdapter.notifyDataSetChanged();
        }else if (MainActivity.ListFoodDetail == null){
            tongtien = 0.0;
            text_tamtinhgiatien.setText(tongtien + " VNĐ");
            text_tien_mat.setText(tongtien + " VNĐ");
            text_tong_cong.setText(tongtien + " VNĐ");
            Toast.makeText(context, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
            dsDHAdapter.notifyDataSetChanged();
        }

    }
    public void updated(){
        MainActivity.ListFoodDetail.size();
        dsDHAdapter.notifyDataSetChanged();
        tonggia();
    }

}