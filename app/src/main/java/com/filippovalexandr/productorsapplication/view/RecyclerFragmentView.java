package com.filippovalexandr.productorsapplication.view;

import com.arellomobile.mvp.MvpView;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;

import io.realm.RealmResults;

public interface RecyclerFragmentView extends MvpView {

    void setCarModelData(RealmResults<GetAllCarsDTO> getAllCarsDTOList, RealmResults<GetModelInfoDTO> getModelInfoDTOList);

    void showConnectionMessage();

    void errorMessage();
}
