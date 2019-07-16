package com.filippovalexandr.productorsapplication.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.fragments.RecyclerViewFragment;
import com.filippovalexandr.productorsapplication.fragments.SingleButtonFragment;
import com.filippovalexandr.productorsapplication.presenter.ProductorsActivityPresenter;
import com.filippovalexandr.productorsapplication.utils.Utils;
import com.filippovalexandr.productorsapplication.view.ProductorsActivityView;
/*
Created by Alexandr Filippov
 */

public class ProductorsActivity extends MvpAppCompatActivity implements ProductorsActivityView, SingleButtonFragment.changeFragment {


    @InjectPresenter
    ProductorsActivityPresenter mProductorsActivityPresenter;

    private Bundle mBundle = new Bundle();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productors_activity);
        mProductorsActivityPresenter.addFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void addFragmentSingleButton() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, SingleButtonFragment.newInstance())
                .commit();
    }

    @Override
    public void replaceFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RecyclerViewFragment.newInstance(mBundle))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void forwardtoRv(String key) {
        if (key.equals(Utils.RV_SCREEN)) {
            mProductorsActivityPresenter.replaceRv();
        }
    }


}
