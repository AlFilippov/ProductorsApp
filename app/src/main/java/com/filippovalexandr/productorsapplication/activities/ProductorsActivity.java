package com.filippovalexandr.productorsapplication.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.fragments.RecyclerViewFragment;
import com.filippovalexandr.productorsapplication.fragments.SingleButtonFragment;
import com.filippovalexandr.productorsapplication.network.RetrofitConfig;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;
import com.filippovalexandr.productorsapplication.presenter.ProductorsActivityPresenter;
import com.filippovalexandr.productorsapplication.utils.Utils;
import com.filippovalexandr.productorsapplication.view.ProductorsActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductorsActivity extends MvpAppCompatActivity implements ProductorsActivityView, SingleButtonFragment.changeFragment {
    @InjectPresenter
    ProductorsActivityPresenter mProductorsActivityPresenter;
    private RealmResults<GetAllCarsDTO> mGetAllCarsDTOS;
    private Realm mRealm;
    private Bundle mBundle = new Bundle();
    public List<GetAllCarsDTO> allCarsList = new ArrayList<>();
    public List<GetModelInfoDTO> carsInfoList = new ArrayList<>();
    public HashMap<String, List<GetAllCarsDTO>> mHashMapAllCars = new HashMap<>();
    public HashMap<String, List<GetModelInfoDTO>> mHashMapCarInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productors_activity);
        mProductorsActivityPresenter.addFragment();
        loadDataAllCars();
        loadDataCarInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void addFragmentSingleButton() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, SingleButtonFragment.newInstance())
                .commit();
    }

    @Override
    public void replaceFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RecyclerViewFragment.newInstance(mBundle))
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void forwardtoRv(String key) {
        if (key.equals("rv")) {
            mProductorsActivityPresenter.replaceRv();
        }
    }

    public void loadDataAllCars() {
        RetrofitConfig.restMockApi().getAllCars().enqueue(new Callback<List<GetAllCarsDTO>>() {
            @Override
            public void onResponse(Call<List<GetAllCarsDTO>> call, Response<List<GetAllCarsDTO>> response) {
                allCarsList.addAll(response.body());
                mHashMapAllCars.put(Utils.GET_ALLCARS, allCarsList);
                putHashMapinBundle(Utils.GET_ALLCARS, mHashMapAllCars);
            }

            @Override
            public void onFailure(Call<List<GetAllCarsDTO>> call, Throwable t) {

            }
        });


    }

    public void loadDataCarInfo() {
        RetrofitConfig.restMockApi().getModel().enqueue(new Callback<List<GetModelInfoDTO>>() {
            @Override
            public void onResponse(Call<List<GetModelInfoDTO>> call, Response<List<GetModelInfoDTO>> response) {
                carsInfoList.addAll(response.body());
                mHashMapCarInfo.put(Utils.GET_ALLCARS, carsInfoList);
                putHashMapinBundle(Utils.GET_INFO, mHashMapCarInfo);

            }

            @Override
            public void onFailure(Call<List<GetModelInfoDTO>> call, Throwable t) {

            }
        });
    }

    public void putHashMapinBundle(String key, HashMap hashMap) {
        mBundle.putSerializable(key, hashMap);
    }
/*
    public void saveData(List<GetAllCarsDTO> resourcesAllCarr, List<GetModelInfoDTO> resourcesCarInfo) {
        Realm.init(ProductorsActivity.this);
        RealmConfiguration config = new RealmConfiguration.Builder().
                name("allcars.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
        mRealm = Realm.getInstance(config);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(resourcesAllCarr);
        mRealm.copyToRealmOrUpdate(resourcesCarInfo);
        mRealm.commitTransaction();
        mRealm.close();
    }
    */
}
