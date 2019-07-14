package com.filippovalexandr.productorsapplication.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
/*
Указать стратегии для ViewState
 */
public interface RecyclerFragmentView extends MvpView {

void addAdapter();
}
