package com.filippovalexandr.productorsapplication.network;



import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;

import java.util.List;

import retrofit2.Call;
public interface IMockApi {
    Call<List<GetAllCarsDTO>> getAllCars();
    Call<List<GetModelInfoDTO>> getModel();
}
