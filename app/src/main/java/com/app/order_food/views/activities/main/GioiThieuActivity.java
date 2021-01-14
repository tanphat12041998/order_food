package com.app.order_food.views.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.order_food.R;
import com.app.order_food.views.activities.BaseActivity;

public class GioiThieuActivity extends BaseActivity {
    Toolbar text_gioi_thieu;

    @Override
    protected void initialViewComponent() {
        text_gioi_thieu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void initialVariables() {
        text_gioi_thieu = findViewById(R.id.text_gioi_thieu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gioi_thieu;
    }
}