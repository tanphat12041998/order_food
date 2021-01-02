package com.app.order_food.views.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initialVariables();
        initialViewComponent();
    }

    protected abstract void initialViewComponent();

    protected abstract void initialVariables();

    protected abstract int getLayoutId();
}
