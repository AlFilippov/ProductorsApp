package com.filippovalexandr.productorsapplication.network;

import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelsDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RESTService {
@GET("v1/cars")
    Call<GetAllCarsDTO>getAllCars();
@GET("v1/car_model_ref")
    Call<GetModelsDTO>getAllModel();

}
