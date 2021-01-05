package com.app.order_food.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.Users;
import com.app.order_food.components.recyclerview.adapter.DSmonanAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    //    Users user;
//    List<Users> usersList= new ArrayList<>();
//    EditText edtemail, edtpassword;
//    Button button;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);



    EditText edtemail, edtname, edtpass, edtpassagain, edtphone, edtaddress;
    Button button;
    List<Users> usersList= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
//        edtemail = view.findViewById(R.id.email);
//        edtpassword = view.findViewById(R.id.pass);
//        button = view.findViewById(R.id.btn);
//        usersList = new ArrayList<>();
//        callAllUser();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickLogin();
//            }
//
//        });

        edtemail = view.findViewById(R.id.resemail);
        edtname = view.findViewById(R.id.resename);
        edtpass = view.findViewById(R.id.respass);
        edtpassagain = view.findViewById(R.id.respassagain);
        edtphone = view.findViewById(R.id.resphone);
        edtaddress = view.findViewById(R.id.resaddress);
        button = view.findViewById(R.id.resbtn);
//        callAllUser();
        callAllResUser();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRes();
            }
        });
        return view;
    }

    public void clickRes(){
        String email = edtemail.getText().toString().trim();
        String name = edtname.getText().toString().trim();
        String password = edtpass.getText().toString().trim();
        String passwordagain = edtpassagain.getText().toString().trim();
        String phone = edtphone.getText().toString().trim();
        String address = edtaddress.getText().toString().trim();
        String img = "abc.jpg";
        if(usersList == null || usersList.isEmpty()){
            return;
        }
        for (Users users: usersList){
            if(email.equals(users.getEmail())) {
                Toast.makeText(getContext(), "Email này đã tồn tại", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(passwordagain) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
            Toast.makeText(getContext(), "Thông tin bạn nhập chưa đủ", Toast.LENGTH_SHORT).show();
        }else if(password.length() <= 5 || passwordagain.length() <= 5){
            Toast.makeText(getContext(), "Mật khẩu ít nhất có 6 kí tự", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(passwordagain)){
            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        }else {
            callAddUser(email, name, password, phone, address, img);
        }



    }



    //    public void clickLogin(){
//        String email = edtemail.getText().toString().trim();
//        String password = edtpassword.getText().toString().trim();
//        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//            Toast.makeText(getContext(), "Email hoặc mật khẩu bạn nhập chưa đúng", Toast.LENGTH_SHORT).show();
//        }
//        if(usersList == null || usersList.isEmpty()){
//            return;
//        }
//        for (Users users: usersList){
//            if(email.equals(users.getEmail()) && password.equals(users.getPassword())){
//                user = users;
//                Toast.makeText(getContext(), user.getEmail()+" "+ user.getName()  , Toast.LENGTH_SHORT).show();
//                break;
//            }
//
//        }
//
//
//    }
//
//    public void callAllUser() {
//        api.getAllUsers().enqueue(new Callback<List<Users>>() {
//            @Override
//            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                usersList = response.body();
//            }
//            @Override
//            public void onFailure(Call<List<Users>> call, Throwable t) {
//
//            }
//        });
//    }
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
                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}