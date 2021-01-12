package com.app.order_food.views.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.app.order_food.API.Api;
import com.app.order_food.API.RetrofitClient;
import com.app.order_food.R;
import com.app.order_food.views.activities.main.ChangeInfoActivity;
import com.app.order_food.views.activities.main.ChangePassActivity;
import com.app.order_food.views.activities.main.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinUserFragment extends BaseFragment {
    CircleImageView Circle_Image_view;
    TextView text_setting_user, text_doithongtin, text_ten_chitiet, text_diachi_chitiet, text_sodienthoai_chitiet, text_doimatkhau;
    private static String EMAiL = "Email";
    String email, img;
    Toolbar title_taikhoan;
    RetrofitClient retrofit = new RetrofitClient();
    Api api = retrofit.getClient().create(Api.class);
    @Override
    protected void initialVariables() {
        Circle_Image_view = getView().findViewById(R.id.Circle_Image_view);
        text_setting_user = getView().findViewById(R.id.text_setting_user);
        text_doithongtin = getView().findViewById(R.id.text_doithongtin);
        text_doimatkhau = getView().findViewById(R.id.text_doimatkhau);
        text_ten_chitiet = getView().findViewById(R.id.text_ten_chitiet);
        text_diachi_chitiet = getView().findViewById(R.id.text_diachi_chitiet);
        text_sodienthoai_chitiet = getView().findViewById(R.id.text_sodienthoai_chitiet);
        title_taikhoan = getView().findViewById(R.id.title_taikhoan);
        Bundle bundle = getArguments();
        email = bundle.getString(EMAiL);

    }

    @Override
    protected void initialViewComponent() {


        text_setting_user.setText(MainActivity.Name);
        text_diachi_chitiet.setText(MainActivity.Address);
        text_ten_chitiet.setText(MainActivity.Name);
        text_sodienthoai_chitiet.setText(MainActivity.Phone);
        new GetImage(Circle_Image_view).execute(MainActivity.Img);

        text_doithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangeInfoActivity.class));
            }
        });

        text_doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePassActivity.class));
            }
        });

        title_taikhoan.setNavigationIcon(R.drawable.ic_back_white);
        title_taikhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_chitiet;
    }

    public static ThongTinUserFragment newInstance(String email) {
        Bundle args = new Bundle();
        ThongTinUserFragment fragment = new ThongTinUserFragment();
        args.putString(EMAiL, email);
        fragment.setArguments(args);
        return fragment;
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
