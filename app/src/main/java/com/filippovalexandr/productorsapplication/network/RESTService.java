package com.filippovalexandr.productorsapplication.network;

import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RESTService {
@GET("v1/cars")
    Call<List<GetAllCarsDTO>>getAllCars();
@GET("v1/car_model_ref")
    Call<List<GetModelInfoDTO>>getModel();

}
