package com.filippovalexandr.productorsapplication.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.filippovalexandr.productorsapplication.view.ProductorsActivityView;

@InjectViewState
public class ProductorsActivityPresenter extends MvpPresenter<ProductorsActivityView> {

public void addFragment(){
    getViewState().addFragmentSingleButton();
}
public void replaceRv(){
    getViewState().replaceFragment();
}

}
