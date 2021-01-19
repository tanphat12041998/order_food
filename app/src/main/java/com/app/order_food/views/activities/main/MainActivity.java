package com.app.order_food.views.activities.main;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.order_food.R;

import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.OrderFoodDetails;
import com.app.order_food.views.activities.BaseActivity;
import com.app.order_food.views.fragments.AccountFragment;
import com.app.order_food.views.fragments.CartFragment;
import com.app.order_food.views.fragments.HomeFragment;
import com.app.order_food.views.fragments.ThongTinUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    public static Integer ID;
    public static String Email;
    public static String Name;
    public static String Phone;
    public static String Address;
    public static String Password;
    public static String Img;
    public static ArrayList<OrderFoodDetails> ListFoodDetail;
    public static BottomNavigationView bottomNavigationView ;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout button_sheet;
    public static TextView slmon, slgia;
    public static String abc;
    @Override
    protected void initialViewComponent() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        button_sheet = findViewById(R.id.button_sheet);
        slmon = findViewById(R.id.slmon);
        slgia = findViewById(R.id.slgia);
        abc = "";
        button_sheet.setVisibility(View.INVISIBLE);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_cart:
                        selectedFragment = new CartFragment();
                        abc = "2";
                        break;
                    case R.id.nav_user:
                        selectedFragment = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };

    @Override
    protected void initialVariables() {
        Intent intent = getIntent();
        ID = intent.getExtras().getInt("ID");
        Email = intent.getExtras().getString("Email");
        Name = intent.getExtras().getString("Name");
        Phone = intent.getExtras().getString("Phone");
        Address = intent.getExtras().getString("Address");
        Password = intent.getExtras().getString("Password");
        Img = intent.getExtras().getString("Img");

        if(ListFoodDetail != null){

        }else {
            ListFoodDetail = new ArrayList<>();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Bấm lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
    }

}