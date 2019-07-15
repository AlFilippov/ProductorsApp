package com.filippovalexandr.productorsapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.model.database.DataBaseController.RealmController;
import com.filippovalexandr.productorsapplication.network.MockApi;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;
import com.filippovalexandr.productorsapplication.presenter.RecyclerViewPresenter;
import com.filippovalexandr.productorsapplication.utils.DataAdapter;
import com.filippovalexandr.productorsapplication.view.RecyclerFragmentView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/*
Скорее всего понадобиться provide presenter , чтобы передать данные в презентер
 */
public class RecyclerViewFragment extends MvpAppCompatFragment implements RecyclerFragmentView {

    private List<GetAllCarsDTO> mGetAllCarsDTOList = new ArrayList<>();
    private List<GetModelInfoDTO> mGetModelInfoDTOList = new ArrayList<>();
    private DataAdapter mDataAdapter;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
    @InjectPresenter
    RecyclerViewPresenter mRecyclerViewPresenter;
    private RealmController mRealmController;
    private MockApi mMockApi;
    private RecyclerView mRecyclerView;

    @ProvidePresenter
    RecyclerViewPresenter provideRepositoryPresenter() {
        return new RecyclerViewPresenter(mMockApi, mRealmController);

    }

    public static RecyclerViewFragment newInstance(Bundle args) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        recyclerViewFragment.setArguments(args);
        return recyclerViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View qView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        mRecyclerView = qView.findViewById(R.id.rv);
        mRecyclerViewPresenter.getLoadDataCarInfo(true, getContext());
        return qView;
    }


    @Override
    public void setCarModelData(RealmResults<GetAllCarsDTO> getAllCarsDTOList) {
        mDataAdapter = new DataAdapter(getContext(), getAllCarsDTOList);
        mRecyclerView.setAdapter(mDataAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void showConnectionMessage() {

    }
}
