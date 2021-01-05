package com.app.order_food.views.activities.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.app.order_food.R;
import com.app.order_food.components.recyclerview.decorator.CustomDecorator;
import com.app.order_food.views.activities.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void initialViewComponent() {
        // TODO
    }

    @Override
    protected void initialVariables() {
        // TODO
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.logo2);
    Bitmap circularBitmap = CustomDecorator.getRoundedCornerBitmap(bitmap, 100);

    ImageView circularImageView = (ImageView) findViewById(R.id.imgAvatarUser);
        circularImageView.();
}