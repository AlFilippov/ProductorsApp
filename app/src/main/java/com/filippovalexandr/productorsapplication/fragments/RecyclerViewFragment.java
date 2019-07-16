package com.filippovalexandr.productorsapplication.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.model.RealmController;
import com.filippovalexandr.productorsapplication.network.MockApi;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;
import com.filippovalexandr.productorsapplication.presenter.RecyclerViewPresenter;
import com.filippovalexandr.productorsapplication.utils.DataAdapter;
import com.filippovalexandr.productorsapplication.view.RecyclerFragmentView;

import io.realm.RealmResults;

public class RecyclerViewFragment extends MvpAppCompatFragment implements RecyclerFragmentView {


    private DataAdapter mDataAdapter;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
    private RealmController mRealmController;
    private MockApi mMockApi;
    private RecyclerView mRecyclerView;
    private Context mContext;
    @InjectPresenter
    RecyclerViewPresenter mRecyclerViewPresenter;

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
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        dataDisplayOptions(activeNetwork);
        return qView;
    }


    @Override
    public void setCarModelData(RealmResults<GetAllCarsDTO> getAllCarsDTOList, RealmResults<GetModelInfoDTO> getModelInfoDTOList) {
        mDataAdapter = new DataAdapter(getContext(), getAllCarsDTOList, getModelInfoDTOList);
        mRecyclerView.setAdapter(mDataAdapter);
        mDataAdapter.notifyDataSetChanged();
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void showConnectionMessage() {
        Toast.makeText(getContext(), "Старые данные", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorMessage() {
        Toast.makeText(getContext(), "База данных пуста и откл интернет", Toast.LENGTH_SHORT).show();
    }

    public void dataDisplayOptions(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            mRecyclerViewPresenter.getLoadDataCarInfo(true, getContext());
        } else {
            mRecyclerViewPresenter.getLoadDataCarInfo(false, getContext());
        }
    }

    @Override
    public void onDestroy() {
        mRecyclerViewPresenter.closeRealm(mContext);
        super.onDestroy();
    }
}
