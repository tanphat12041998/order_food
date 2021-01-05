package com.app.order_food.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.order_food.R;
import com.app.order_food.views.activities.main.MenuFoodActivity;

public class HomeFragment extends Fragment {
    Integer idType;
    LinearLayout btna,btnb,btnc,btnd,btne,btnf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btna = view.findViewById(R.id.btn_buaSang);
        btnb = view.findViewById(R.id.btn_buaTrua);
        btnc = view.findViewById(R.id.btn_buaChieu);
        btnd = view.findViewById(R.id.btn_banhmi);
        btne = view.findViewById(R.id.btn_mipho);
        btnf = view.findViewById(R.id.btn_xienque);

        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 1;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 2;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 3;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 4;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 5;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idType = 6;
                Intent intent = new Intent(getActivity(), MenuFoodActivity.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });

        return view;
    }
}