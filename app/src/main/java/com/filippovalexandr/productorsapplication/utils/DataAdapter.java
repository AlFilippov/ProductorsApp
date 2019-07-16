package com.filippovalexandr.productorsapplication.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.filippovalexandr.productorsapplication.R;
import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;

import io.realm.RealmResults;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private RealmResults<GetAllCarsDTO> mGetAllCarsDTOList;
    private RealmResults<GetModelInfoDTO> mGetModelInfoDTOList;

    public DataAdapter(Context context, RealmResults<GetAllCarsDTO> getAllCarsDTOList, RealmResults<GetModelInfoDTO> getModelInfoDTOList) {
        mContext = context;
        mGetAllCarsDTOList = getAllCarsDTOList;
        mGetModelInfoDTOList = getModelInfoDTOList;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_rv_fragment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mOwnerCar.setText(mGetAllCarsDTOList.get(position).getOwner());
        viewHolder.mIdCar.setText(mGetAllCarsDTOList.get(position).getId());
        viewHolder.mModelCar.setText(mGetModelInfoDTOList.get(mGetAllCarsDTOList.get(position).getModelId()).getTitle());
        viewHolder.mYearCar.setText(String.valueOf(mGetAllCarsDTOList.get(position).getYear()));
    }

    @Override
    public int getItemCount() {
        return mGetAllCarsDTOList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mIdCar;
        TextView mModelCar;
        TextView mYearCar;
        TextView mOwnerCar;


        ViewHolder(View view) {
            super(view);
            mIdCar = view.findViewById(R.id.id_car);
            mModelCar = view.findViewById(R.id.model_car);
            mYearCar = view.findViewById(R.id.year_car);
            mOwnerCar = view.findViewById(R.id.owner_car);

        }


    }


}