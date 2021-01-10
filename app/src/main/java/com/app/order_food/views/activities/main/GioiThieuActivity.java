package com.app.order_food.views.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.order_food.R;
import com.app.order_food.views.activities.BaseActivity;

public class GioiThieuActivity extends BaseActivity {
    Button btn_back_gt;

    @Override
    protected void initialViewComponent() {
        btn_back_gt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initialVariables() {
        btn_back_gt = findViewById(R.id.btn_back_gt);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gioi_thieu;
    }
}