package com.filippovalexandr.productorsapplication.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;

import java.util.List;

import io.realm.RealmResults;

/*
Указать стратегии для ViewState
 */
public interface RecyclerFragmentView extends MvpView {

void setCarModelData(RealmResults<GetAllCarsDTO> getAllCarsDTOList);
void showConnectionMessage();
}
