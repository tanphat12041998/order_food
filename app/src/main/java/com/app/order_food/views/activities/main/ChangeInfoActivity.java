package com.app.order_food.views.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Users;
import com.app.order_food.views.activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfoActivity extends BaseActivity {
    EditText edtTenCu, edtSdtCu, edtDiaChiCu;
    Button btn_confirm;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Integer ID = MainActivity.ID;

    @Override
    protected void initialViewComponent() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickConfirm();
            }
        });
    }

    @Override
    protected void initialVariables() {
        edtDiaChiCu = findViewById(R.id.edtDiaChiCu);
        edtTenCu = findViewById(R.id.edtTenCu);
        edtSdtCu = findViewById(R.id.edtSdtCu);
        edtTenCu.setText(MainActivity.Name);
        edtSdtCu.setText(MainActivity.Phone);
        edtDiaChiCu.setText(MainActivity.Address);
        btn_confirm = findViewById(R.id.btn_confirm);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_info;
    }

    public void clickConfirm(){
        String name = edtTenCu.getText().toString();
        String phone = edtSdtCu.getText().toString().trim();
        String address = edtDiaChiCu.getText().toString();
        if (TextUtils.isEmpty(name)  || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
            Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }
        else {
            api.updateUserInfo(ID, name, phone, address).enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    Toast.makeText(ChangeInfoActivity.this, "Cập nhật thành công - Xin đăng nhập lại", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
    }

}