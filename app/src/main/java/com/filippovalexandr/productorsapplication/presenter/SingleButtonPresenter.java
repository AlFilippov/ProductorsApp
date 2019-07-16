package com.filippovalexandr.productorsapplication.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.filippovalexandr.productorsapplication.view.SingleButtonView;

@InjectViewState
public class SingleButtonPresenter extends MvpPresenter<SingleButtonView> {
    public void clickButton() {
        getViewState().showToast();
    }

    public void changeFragment() {
        getViewState().nextFragment();
    }
}


