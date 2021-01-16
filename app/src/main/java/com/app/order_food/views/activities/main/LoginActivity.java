package com.app.order_food.views.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class LoginActivity extends BaseActivity {
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    Users user;
    List<Users> usersList= new ArrayList<>();
    TextView tv_quenmatkhau;
    EditText ed_login_username, ed_login_password;
    Button btn_dangnhap,btn_dangky;
    CheckBox checkbox_login;
    String luuThongTinDangNhap = "thongtindangnhap";

    @Override
    protected void initialViewComponent() {
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResgisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initialVariables() {
        init();
        callAllUser();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences(luuThongTinDangNhap, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String email = ed_login_username.getText().toString().trim();
        String passw = ed_login_password.getText().toString().trim();
        editor.putString("email", email);
        editor.putString("password", passw);
        editor.putBoolean("save", checkbox_login.isChecked());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(luuThongTinDangNhap, MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String pass = preferences.getString("password", "");
        boolean save = preferences.getBoolean("save", false);
        if (save) {
            ed_login_username.setText(email);
            ed_login_password.setText(pass);
        }
        checkbox_login.setChecked(save);
    }

    public void init(){
        ed_login_username = findViewById(R.id.ed_login_username);
        ed_login_password = findViewById(R.id.ed_login_password);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_dangky = findViewById(R.id.btn_dangky);
        checkbox_login = findViewById(R.id.checkbox_login);
        usersList = new ArrayList<>();
    }

    public void clickLogin(){
        String email = ed_login_username.getText().toString().trim();
        String password = ed_login_password.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu bạn nhập chưa đúng", Toast.LENGTH_SHORT).show();
        }
        if(usersList == null || usersList.isEmpty()){
            Toast.makeText(getApplicationContext(), "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }
        for (Users users: usersList){
            if(email.equals(users.getEmail()) && password.equals(users.getPassword())){
                user = users;

                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("ID", user.getId());
                intent.putExtra("Email", user.getEmail());
                intent.putExtra("Address", user.getAddress());
                intent.putExtra("Name", user.getName());
                intent.putExtra("Phone", user.getPhone());
                intent.putExtra("Password", user.getPassword());
                intent.putExtra("Img", user.getImg());
                startActivity(intent);
                finish();
                break;
            }
            else {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

            }


        }
    }
    public void callAllUser() {
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
}