package com.filippovalexandr.productorsapplication.model.database.DataBaseController;

import android.content.Context;

import com.filippovalexandr.productorsapplication.fragments.RecyclerViewFragment;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmController {
    private Realm mRealm;
    private GetAllCarsDTO realmModelAllCars;
    private GetModelInfoDTO realmModelCarsInfo;

    public RealmController(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        mRealm.setDefaultConfiguration(config);
        mRealm = Realm.getDefaultInstance();
    }

    public void addInfoAllCars(String id_cars, int model_id, int year, String owner) {
        mRealm.beginTransaction();
        realmModelAllCars = mRealm.createObject(GetAllCarsDTO.class);
        int id = getNextKeyAllCars();
        realmModelAllCars.setTitle_model(findModel(model_id));
        realmModelAllCars.setIdData(id);
        realmModelAllCars.setId(id_cars);
        realmModelAllCars.setModelId(model_id);
        realmModelAllCars.setYear(year);
        realmModelAllCars.setOwner(owner);
        mRealm.commitTransaction();

    }

    public void addCarsInfo(int id_title, String title) {
        mRealm.beginTransaction();
        GetModelInfoDTO realmModelCarsInfo = mRealm.createObject(GetModelInfoDTO.class);
        int id = getNextKeyCarsInfo();
        realmModelCarsInfo.setId(id_title);
        realmModelCarsInfo.setIdData(id);
        realmModelCarsInfo.setTitle(title);
        mRealm.commitTransaction();
    }

    public RealmResults<GetAllCarsDTO> getInfoAllCars() {
        return mRealm.where(GetAllCarsDTO.class).findAll();
    }

    public RealmResults<GetModelInfoDTO> getCarsModelInfo() {
        return mRealm.where(GetModelInfoDTO.class).findAll();
    }

    private int getNextKeyAllCars() {
        return mRealm.where(GetAllCarsDTO.class).max("idData").intValue() + 1;
    }

    private int getNextKeyCarsInfo() {
        return mRealm.where(GetModelInfoDTO.class).max("idData").intValue() + 1;
    }

    private String findModel(int id) {
        if (realmModelCarsInfo != null) {
            realmModelCarsInfo = mRealm.where(GetModelInfoDTO.class).equalTo("model_id", id).findFirst();
            return realmModelCarsInfo.getTitle();
        } else {
            return null;
        }
    }
}
