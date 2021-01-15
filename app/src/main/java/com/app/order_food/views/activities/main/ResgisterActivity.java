package com.app.order_food.views.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ResgisterActivity extends BaseActivity {
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    List<Users> usersList= new ArrayList<>();
    EditText ed_hoten, ed_phone, ed_diachi, ed_email, ed_password, ed_repassword;
    Button btn_register;
    TextView tv_quaylaidn_register;

    @Override
    protected void initialViewComponent() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRes();
            }
        });
        tv_quaylaidn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResgisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initialVariables() {
        resinit();
        callAllResUser();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    public void resinit(){
        ed_hoten = findViewById(R.id.ed_hoten);
        ed_phone = findViewById(R.id.ed_phone);
        ed_diachi = findViewById(R.id.ed_diachi);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_repassword = findViewById(R.id.ed_repassword);
        btn_register = findViewById(R.id.btn_register);
        tv_quaylaidn_register = findViewById(R.id.tv_quaylaidn_register);
    }

    public void clickRes(){
        String email = ed_email.getText().toString().trim();
        String name = ed_hoten.getText().toString().trim();
        String password = ed_password.getText().toString().trim();
        String passwordagain = ed_repassword.getText().toString().trim();
        String phone = ed_phone.getText().toString().trim();
        String address = ed_diachi.getText().toString().trim();
        String img = "https://previews.123rf.com/images/olgastrelnikova/olgastrelnikova1901/olgastrelnikova190100001/115903194-food-icon-with-smile-label-for-food-company-grocery-store-icon-vector-illustration-with-smiling-mout.jpg";
        if(usersList == null || usersList.isEmpty()){
            return;
        }
        for (Users users: usersList){
            if(email.equals(users.getEmail())) {
                Toast.makeText(getApplicationContext(), "Tài khoản này đã tồn tại", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(passwordagain) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
            Toast.makeText(getApplicationContext(), "Thông tin bạn nhập chưa đủ", Toast.LENGTH_SHORT).show();
        }else if(password.length() <= 5 || passwordagain.length() <= 5){
            Toast.makeText(getApplicationContext(), "Mật khẩu ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();
        }else if(password.length() >=17 || passwordagain.length() >=17 ){
            Toast.makeText(getApplicationContext(), "Mật khẩu tối đa 16 kí tự", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(passwordagain)){
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        }else {
            callAddUser(email, name, password, phone, address, img);
        }



    }

    public void callAllResUser(){
        api.getAllUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                usersList = response.body();
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });

    }

    public void callAddUser(String email, String name, String password, String phone, String address, String img){
        api.addUser(email, name, password, phone, address, img).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResgisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}