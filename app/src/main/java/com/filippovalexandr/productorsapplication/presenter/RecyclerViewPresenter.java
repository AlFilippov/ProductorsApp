package com.filippovalexandr.productorsapplication.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.filippovalexandr.productorsapplication.view.RecyclerFragmentView;
/*
Осталось закинуть данные в Realm и прокинуть их в адаптер списка
Скорее всего понадобиться ProvidePresenter в RecyclerViewFragment
 */
@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<RecyclerFragmentView> {


    public void sortedData() {
        getViewState().addAdapter();
    }

}
