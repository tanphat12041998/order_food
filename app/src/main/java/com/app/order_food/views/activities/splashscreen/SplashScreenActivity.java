package com.app.order_food.views.activities.splashscreen;

import android.content.Intent;
import android.os.Handler;
import com.app.order_food.R;
import com.app.order_food.views.activities.BaseActivity;
import com.app.order_food.views.activities.main.MainActivity;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void initialViewComponent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }

    @Override
    protected void initialVariables() {
        // TODO
    }

    @Override
    protected int getLayoutId() {
        return R.layout.acitvity_splash_screen;
    }
}