package com.filippovalexandr.productorsapplication.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.cicerone.SampleApplication;
import com.filippovalexandr.productorsapplication.fragments.RecyclerViewFragment;
import com.filippovalexandr.productorsapplication.fragments.SingleButtonFragment;
import com.filippovalexandr.productorsapplication.presenter.ProductorsActivityPresenter;
import com.filippovalexandr.productorsapplication.view.ProductorsActivityView;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

import static com.filippovalexandr.productorsapplication.utils.Utils.RV_SCREEN;
import static com.filippovalexandr.productorsapplication.utils.Utils.SINGLE_SCREEN;


public class ProductorsActivity extends MvpAppCompatActivity implements ProductorsActivityView, SingleButtonFragment.changeFragment {
    @InjectPresenter
    ProductorsActivityPresenter mProductorsActivityPresenter;

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
    public void addFragmentSingleButton() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, SingleButtonFragment.newInstance())
                .commit();
    }

    @Override
    public void replaceFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RecyclerViewFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void forwardtoRv(String key) {
        if (key.equals("rv")) {
            mProductorsActivityPresenter.replaceRv();
        }
    }
}
