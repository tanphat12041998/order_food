package com.app.order_food.views.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.components.Model.Foods;
import com.app.order_food.components.Model.Users;
import com.app.order_food.views.fragments.AccountFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinUserActivity extends AppCompatActivity {
    CircleImageView Circle_Image_view;
    TextView text_setting_user, text_doithongtin, text_ten_chitiet, text_diachi_chitiet, text_sodienthoai_chitiet, text_doimatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_chitiet);
        init();

        text_diachi_chitiet.setText(MainActivity.Address);
        text_ten_chitiet.setText(MainActivity.Name);
        text_sodienthoai_chitiet.setText(MainActivity.Phone);
        text_setting_user.setText(MainActivity.Name);
        new GetImage(Circle_Image_view).execute(MainActivity.Img);

        text_doithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangeInfoActivity.class));
            }
        });

        text_doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassActivity.class));
            }
        });

    }

    public void init() {
        Circle_Image_view = findViewById(R.id.Circle_Image_view);
        text_setting_user = findViewById(R.id.text_setting_user);
        text_doithongtin = findViewById(R.id.text_doithongtin);
        text_doimatkhau = findViewById(R.id.text_doimatkhau);
        text_ten_chitiet = findViewById(R.id.text_ten_chitiet);
        text_diachi_chitiet = findViewById(R.id.text_diachi_chitiet);
        text_sodienthoai_chitiet = findViewById(R.id.text_sodienthoai_chitiet);
    }

    public class GetImage extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmap = null;
        CircleImageView circleImageView;

        public GetImage(CircleImageView circleImageView) {
            this.circleImageView = circleImageView;
        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            circleImageView.setImageBitmap(bitmap);
        }
    }
}