package com.app.order_food.views.activities.main;



import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.app.order_food.R;

import com.app.order_food.views.activities.BaseActivity;
import com.app.order_food.views.fragments.AccountFragment;
import com.app.order_food.views.fragments.CartFragment;
import com.app.order_food.views.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
    public static Integer ID;
    public static String Email;
    public static String Name;
    public static String Phone;
    public static String Address;
    public static String Password;
    public static String Img;
    @Override
    protected void initialViewComponent() {
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

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }
}