package com.filippovalexandr.productorsapplication.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.filippovalexandr.productorsapplication.model.RealmController;
import com.filippovalexandr.productorsapplication.network.MockApi;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;
import com.filippovalexandr.productorsapplication.view.RecyclerFragmentView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Без контекста тут не обошлось :(
 */
@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<RecyclerFragmentView> {
    private final MockApi mMockApi;
    private final RealmController mRealmController;


    public RecyclerViewPresenter(MockApi mockApi, RealmController realmController) {
        mMockApi = new MockApi();
        mRealmController = realmController;
    }

    public void getLoadDataCarInfo(boolean isLoad, Context context) {


        if (isLoad) {

            mMockApi.getModel().enqueue(new Callback<List<GetModelInfoDTO>>() {
                @Override
                public void onResponse(Call<List<GetModelInfoDTO>> call, Response<List<GetModelInfoDTO>> response) {
                    List<GetModelInfoDTO> getModelInfoDTOList = new ArrayList<>();
                    getModelInfoDTOList.addAll(response.body());
                    initRealm(context).addModelInfoNetWork(getModelInfoDTOList);
                }

                @Override
                public void onFailure(Call<List<GetModelInfoDTO>> call, Throwable t) {

                }
            });
            mMockApi.getAllCars().enqueue(new Callback<List<GetAllCarsDTO>>() {
                @Override
                public void onResponse(Call<List<GetAllCarsDTO>> call, Response<List<GetAllCarsDTO>> response) {
                    List<GetAllCarsDTO> getAllCarsDTOList = new ArrayList<>();
                    getAllCarsDTOList.addAll(response.body());
                    initRealm(context).addAllCarsNetwork(getAllCarsDTOList);
                    getViewState().setCarModelData(initRealm(context).getInfoAllCars(), initRealm(context).getCarsModelInfo());
                }

                @Override
                public void onFailure(Call<List<GetAllCarsDTO>> call, Throwable t) {

                }
            });


        } else {
            showErrorMessage(initRealm(context).getInfoAllCars(), context);
        }


    }

    private void setTitleAllCars(RealmController realmController) {


        for (int i = 0; i <= realmController.getInfoAllCars().size(); i++) {
            long id = realmController.getInfoAllCars().get(i).getModelId();
            realmController.addTitleAllCars(realmController.findModel(id));

        }

    }

    public void closeRealm(Context context) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.close();
    }

    private void showErrorMessage(RealmResults<GetAllCarsDTO> getAllCarsDTOS, Context context) {
        if (!getAllCarsDTOS.isEmpty()) {
            getViewState().setCarModelData(initRealm(context).getInfoAllCars(), initRealm(context).getCarsModelInfo());
            getViewState().showConnectionMessage();
        } else {
            getViewState().errorMessage();
        }
    }

    private void deleteAllRealm(Context context) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private RealmController initRealm(Context context) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        return new RealmController(realm);
    }


}