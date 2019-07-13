package com.filippovalexandr.productorsapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

public class RecyclerViewFragment extends MvpAppCompatFragment {
    public static RecyclerViewFragment newInstance(){
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        Bundle args =new Bundle();
        recyclerViewFragment.setArguments(args);
        return recyclerViewFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
