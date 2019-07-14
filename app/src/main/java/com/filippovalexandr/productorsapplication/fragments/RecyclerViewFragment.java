package com.filippovalexandr.productorsapplication.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.model.database.DataBaseController.RealmController;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;
import com.filippovalexandr.productorsapplication.presenter.RecyclerViewPresenter;
import com.filippovalexandr.productorsapplication.utils.DataAdapter;
import com.filippovalexandr.productorsapplication.utils.Utils;
import com.filippovalexandr.productorsapplication.view.RecyclerFragmentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
Скорее всего понадобиться provide presenter , чтобы передать данные в презентер
 */
public class RecyclerViewFragment extends MvpAppCompatFragment implements RecyclerFragmentView {
    private HashMap<String, List<GetAllCarsDTO>> mHashMapAllCars;
    private HashMap<String, List<GetModelInfoDTO>> mHashMapCarInfo;
    private List<GetAllCarsDTO> mGetAllCarsDTOList = new ArrayList<>();
    private List<GetModelInfoDTO> mGetModelInfoDTOList = new ArrayList<>();
    private DataAdapter mDataAdapter;
    private RealmController mRealmController;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
    @InjectPresenter
    RecyclerViewPresenter mRecyclerViewPresenter;

    public static RecyclerViewFragment newInstance(Bundle args) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        recyclerViewFragment.setArguments(args);
        return recyclerViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = new Bundle(getArguments());
        mHashMapAllCars = (HashMap<String, List<GetAllCarsDTO>>) bundle.getSerializable(Utils.GET_ALLCARS);
        mHashMapCarInfo = (HashMap<String, List<GetModelInfoDTO>>) bundle.getSerializable(Utils.GET_INFO);
        mGetAllCarsDTOList = mHashMapAllCars.get(Utils.GET_ALLCARS);
        mGetModelInfoDTOList = mHashMapCarInfo.get(Utils.GET_INFO);
        mRecyclerViewPresenter.sortedData();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View qView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        RecyclerView recyclerView = qView.findViewById(R.id.rv);
        recyclerView.setAdapter(mDataAdapter);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void addAdapter() {
        mDataAdapter = new DataAdapter(getContext(), new RealmController(getContext()).getInfoAllCars());
    }


}
