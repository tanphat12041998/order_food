package com.app.order_food.views.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.order_food.R;
import com.app.order_food.views.activities.main.MainActivity;

public class TimKiemFragment extends BaseFragment{
    TextView text_huy_tim_kiem;
    RecyclerView recyclerview_timkiem, recyclerview_show;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initialVariables() {
        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
        text_huy_tim_kiem = getView().findViewById(R.id.text_huy_tim_kiem);


    }

    @Override
    protected void initialViewComponent() {

        text_huy_tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });



    }

    public static TimKiemFragment newInstance() {
        Bundle args = new Bundle();
        TimKiemFragment fragment = new TimKiemFragment();
        fragment.setArguments(args);
        return fragment;

    }
}
