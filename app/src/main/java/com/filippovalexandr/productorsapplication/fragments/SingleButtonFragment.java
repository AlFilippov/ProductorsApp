package com.filippovalexandr.productorsapplication.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.databind.FragmentSingleButtonBind;
import com.filippovalexandr.productorsapplication.databinding.FragmentSingleButtonBinding;
import com.filippovalexandr.productorsapplication.presenter.SingleButtonPresenter;
import com.filippovalexandr.productorsapplication.view.SingleButtonView;

public class SingleButtonFragment extends MvpAppCompatFragment implements SingleButtonView {
    private Button singleButton;
    @InjectPresenter
    SingleButtonPresenter mSingleButtonPresenter;

    public static SingleButtonFragment newInstance() {
        SingleButtonFragment singleButtonFragment = new SingleButtonFragment();
        Bundle args = new Bundle();
        singleButtonFragment.setArguments(args);
        return singleButtonFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSingleButtonBinding fragmentSingleButtonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_single_button, container, false);
        fragmentSingleButtonBinding.setFragmentSingleButtonDB(new FragmentSingleButtonBind("Поехали"));
        singleButton = fragmentSingleButtonBinding.singleButton;
        fragmentSingleButtonBinding.setOnClick(v -> {
            mSingleButtonPresenter.clickButton();
            mSingleButtonPresenter.changeFragment();

        });
        return fragmentSingleButtonBinding.getRoot();
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), "Поехали за машинами", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void nextFragment() {
        mChangeFragment.forwardtoRv("rv");
    }

    public interface changeFragment {
        void forwardtoRv(String key);
    }

    changeFragment mChangeFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mChangeFragment = (changeFragment) context;

    }
}
