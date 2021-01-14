package com.app.order_food.views.activities.main;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Users;
import com.app.order_food.views.activities.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassActivity extends BaseActivity {
    EditText edtMatKhauCu, edtpassword_change, edtpassword_change_again;
    Button btn_passchange_confirm, btn_passchange_cancel;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    String PASS = MainActivity.Password;
    Integer ID = MainActivity.ID;
    Toolbar title_taikhoanmk;
    @Override
    protected void initialViewComponent() {
        btn_passchange_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickconfirm();
            }
        });
        title_taikhoanmk.setNavigationIcon(R.drawable.ic_close);
        title_taikhoanmk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initialVariables() {
        edtMatKhauCu = findViewById(R.id.edtMatKhauCu);
        edtpassword_change = findViewById(R.id.edtpassword_change);
        edtpassword_change_again = findViewById(R.id.edtpassword_change_again);
        btn_passchange_confirm = findViewById(R.id.btn_passchange_confirm);
        title_taikhoanmk = findViewById(R.id.title_taikhoanmk);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pass;
    }

    public void clickconfirm(){
        String pass = edtMatKhauCu.getText().toString();
        String password = edtpassword_change.getText().toString().trim();
        String passwordagain = edtpassword_change_again.getText().toString();
        if (TextUtils.isEmpty(pass)  || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordagain)) {
            Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else if(password.length() <= 5 || passwordagain.length() <= 5){
            Toast.makeText(getApplicationContext(), "Mật khẩu ít nhất có 6 kí tự", Toast.LENGTH_SHORT).show();
        }else if (!PASS.equals(pass)){
            Toast.makeText(getApplicationContext(), "Mật khẩu cũ không trùng khớp", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(passwordagain)){
            Toast.makeText(getApplicationContext(), "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
        } else {
            api.updateUserPass(ID, password).enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    Toast.makeText(ChangePassActivity.this, "Cập nhật thành công - Xin đăng nhập lại", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }

    }
}