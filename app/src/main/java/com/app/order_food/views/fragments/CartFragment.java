package com.app.order_food.views.fragments;

import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

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
    Button button_thanhtoan, btn_thay;
    EditText thay_ten, thay_phone, thay_address;
    ListView listview_dsdonhang;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Toolbar title_don_hang_cua_toi;
    DsDHAdapter dsDHAdapter;
    String ghi_chu, name, phone, address;
    Integer idpayment = 1, tongtien = 0, id = 1;
    Integer iduser;

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
        text_thay_doi = getView().findViewById(R.id.text_thay_doi);
        text_tamtinhgiatien = getView().findViewById(R.id.text_tam_tinh_gia_tien);
        text_tong_cong = getView().findViewById(R.id.text_tong_cong);
        button_thanhtoan = getView().findViewById(R.id.button_thanhtoan);
        listview_dsdonhang = getView().findViewById(R.id.listview_dsdonhang);
        text_gio_hang_trong = getView().findViewById(R.id.text_gio_hang_trong);
        title_don_hang_cua_toi = getView().findViewById(R.id.title_don_hang_cua_toi);
    }

    @Override
    protected void initialViewComponent() {
        text_ten_nguoi_dung_don_hang.setText(MainActivity.Name);
        text_so_dien_thoai_don_hang.setText(MainActivity.Phone);
        text_diachi_donhang.setText(MainActivity.Address);
        dsDHAdapter = new DsDHAdapter(CartFragment.this, MainActivity.ListFoodDetail);
        listview_dsdonhang.setAdapter(dsDHAdapter);
        text_thay_doi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_thaydoi);
                btn_thay = dialog.findViewById(R.id.btn_thay);
                thay_ten = dialog.findViewById(R.id.thay_ten);
                thay_phone = dialog.findViewById(R.id.thay_phone);
                thay_address = dialog.findViewById(R.id.thay_address);
                thay_address.setText(MainActivity.Address);
                thay_phone.setText(MainActivity.Phone);
                thay_ten.setText(MainActivity.Name);
                btn_thay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tname = thay_ten.getText().toString().trim();
                        String tphone = thay_phone.getText().toString().trim();
                        String taddress = thay_address.getText().toString().trim();
                        if (TextUtils.isEmpty(tname) || TextUtils.isEmpty(tphone) || TextUtils.isEmpty(taddress)) {
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        } else {
                            text_ten_nguoi_dung_don_hang.setText(tname);
                            text_so_dien_thoai_don_hang.setText(tphone);
                            text_diachi_donhang.setText(taddress);
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

        if (MainActivity.ListFoodDetail.size() <= 0) {
            text_gio_hang_trong.setText("Giỏ hàng trống");
            text_gio_hang_trong.setVisibility(View.VISIBLE);
            listview_dsdonhang.setVisibility(View.INVISIBLE);
        } else {
            text_gio_hang_trong.setVisibility(View.INVISIBLE);
            listview_dsdonhang.setVisibility(View.VISIBLE);
        }
        tonggia();
        button_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer status = 0;
                id = id + 1;
                iduser = MainActivity.ID;
                ghi_chu = text_ghichu.getText().toString().trim();
                name = text_ten_nguoi_dung_don_hang.getText().toString().trim();
                phone = text_so_dien_thoai_don_hang.getText().toString().trim();
                address = text_diachi_donhang.getText().toString().trim();
                Calendar cal = Calendar.getInstance(Locale.getDefault());
                String dateTime = DateFormat.format("yyyy/MM/dd", cal).toString();

                if (MainActivity.ListFoodDetail.size() <= 0) {
                    Toast.makeText(getContext(), "Giỏ hàng trống - không thể đặt", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
                    Toast.makeText(getContext(), "Không để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {

                    for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
                        api.addOrderFood(id, iduser, idpayment, MainActivity.ListFoodDetail.get(i).getId(), dateTime
                                , MainActivity.ListFoodDetail.get(i).getGiatong()
                                , MainActivity.ListFoodDetail.get(i).getSl(), ghi_chu, name, phone, address, MainActivity.ListFoodDetail.get(i).getTen(), status)
                                .enqueue(new Callback<OrderFoods>() {
                                    @Override
                                    public void onResponse(Call<OrderFoods> call, Response<OrderFoods> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<OrderFoods> call, Throwable t) {

                                    }
                                });
                    }
                    Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    MainActivity.ListFoodDetail.clear();
                    text_ghichu.setText("");
                    updated();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.main, LichSuFragment.newInstance());
                    fragmentTransaction.addToBackStack(LichSuFragment.class.getSimpleName());
                    fragmentTransaction.commit();
                }
            }
        });
        updated();
    }


    public void tonggia() {
        if (MainActivity.ListFoodDetail.size() <= 0) {

            tongtien = 0;
            text_tamtinhgiatien.setText(tongtien + " VNĐ");
            text_tong_cong.setText(tongtien + " VNĐ");
        } else {
            tongtien = 0;
            for (int i = 0; i < MainActivity.ListFoodDetail.size(); i++) {
                tongtien += MainActivity.ListFoodDetail.get(i).getGiatong();
                text_tamtinhgiatien.setText(tongtien + " VNĐ");
                text_tong_cong.setText(tongtien + " VNĐ");
            }

        }

    }

    public void updated() {
        dsDHAdapter = new DsDHAdapter(CartFragment.this, MainActivity.ListFoodDetail);
        listview_dsdonhang.setAdapter(dsDHAdapter);
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
        id = sharedPref.getInt("id", id);
        if (id == null) {
        }
        id = id + 1;
    }
}