package com.filippovalexandr.productorsapplication.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SingleButtonView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showToast();
@StateStrategyType(OneExecutionStateStrategy.class)
    void nextFragment();
}
