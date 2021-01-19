package com.app.order_food.views.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.order_food.R;
import com.app.order_food.views.activities.main.GioiThieuActivity;
import com.app.order_food.views.activities.main.LoginActivity;
import com.app.order_food.views.activities.main.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends BaseFragment {
    CircleImageView Circle_Image_view_user;
    TextView text_setting_user;
    Button button_thong_tin_user, button_lich_su, button_cai_dat, button_gioi_thieu, button_dang_xuat;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initialVariables() {
        Circle_Image_view_user = getView().findViewById(R.id.Circle_Image_view_user);
        text_setting_user = getView().findViewById(R.id.text_setting_user);
        button_thong_tin_user = getView().findViewById(R.id.button_thong_tin_user);
        button_lich_su = getView().findViewById(R.id.button_lich_su);
        button_cai_dat = getView().findViewById(R.id.button_cai_dat);
        button_gioi_thieu = getView().findViewById(R.id.button_gioi_thieu);
        button_dang_xuat = getView().findViewById(R.id.button_dang_xuat);
        text_setting_user.setText(MainActivity.Name);
        new GetImage(Circle_Image_view_user).execute(MainActivity.Img);
        button();
    }

    @Override
    protected void initialViewComponent() {
        MainActivity.button_sheet.setVisibility(View.INVISIBLE);
    }



    public void button() {
        button_dang_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        button_gioi_thieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GioiThieuActivity.class));
            }
        });
        button_lich_su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), LichSuActivity.class));
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, LichSuFragment.newInstance());
                fragmentTransaction.addToBackStack(LichSuFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });
        button_cai_dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.add(R.id.main, CaiDatFragment.newInstance());
//                fragmentTransaction.addToBackStack(CaiDatFragment.class.getSimpleName());
//                fragmentTransaction.commit();
            }
        });

        button_thong_tin_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main, ThongTinUserFragment.newInstance(MainActivity.Email));
                fragmentTransaction.addToBackStack(ThongTinUserFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });

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