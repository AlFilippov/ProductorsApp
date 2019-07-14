package com.filippovalexandr.productorsapplication.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;

public interface ProductorsActivityView extends MvpView {
    void addFragmentSingleButton();
    void replaceFragment();
}
