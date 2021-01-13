package com.app.order_food.views.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.app.order_food.R;
import com.app.order_food.views.fragments.BaseFragment;

public class CaiDatFragment extends BaseFragment {
    Toolbar text_cai_dat;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cai_dat;
    }

    @Override
    protected void initialVariables() {
        text_cai_dat = getView().findViewById(R.id.text_cai_dat);
    }

    @Override
    protected void initialViewComponent() {
        text_cai_dat.setNavigationIcon(R.drawable.ic_back_white);
        text_cai_dat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    public static CaiDatFragment newInstance() {
        Bundle args = new Bundle();
        CaiDatFragment fragment = new CaiDatFragment();
        fragment.setArguments(args);
        return fragment;
    }
}