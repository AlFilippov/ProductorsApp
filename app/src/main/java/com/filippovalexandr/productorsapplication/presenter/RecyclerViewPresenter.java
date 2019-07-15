package com.filippovalexandr.productorsapplication.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.filippovalexandr.productorsapplication.model.database.DataBaseController.RealmController;
import com.filippovalexandr.productorsapplication.network.MockApi;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;
import com.filippovalexandr.productorsapplication.view.RecyclerFragmentView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*

Добавить взаимодействие с БД , и интерфейсами
 */
@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<RecyclerFragmentView> {
    private final MockApi mMockApi;
    private final RealmController mRealmController;
    private Realm mRealm;
    private List<GetAllCarsDTO> mGetAllCarsDTOS;


    public RecyclerViewPresenter(MockApi mockApi, RealmController realmController) {
        mMockApi = new MockApi();
        mRealmController = realmController;
    }

    public void getLoadDataCarInfo(boolean isLoad, Context context) {


        if (isLoad) {
            mMockApi.getAllCars().enqueue(new Callback<List<GetAllCarsDTO>>() {
                @Override
                public void onResponse(Call<List<GetAllCarsDTO>> call, Response<List<GetAllCarsDTO>> response) {
                    List<GetAllCarsDTO> getAllCarsDTOList = new ArrayList<>();
                    getAllCarsDTOList.addAll(response.body());
                    Realm.init(context);
                    Realm realm = Realm.getDefaultInstance();
                    RealmController realmController = new RealmController(realm);
                    realmController.addAllCarsNetwork(getAllCarsDTOList);

                    getViewState().setCarModelData(realmController.getInfoAllCars());

                }

                @Override
                public void onFailure(Call<List<GetAllCarsDTO>> call, Throwable t) {

                }
            });

        }


    }


}