package com.filippovalexandr.productorsapplication.network;

import com.filippovalexandr.productorsapplication.network.dto.GetAllCarsDTO;
import com.filippovalexandr.productorsapplication.network.dto.GetModelInfoDTO;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


public class MockApi implements IMockApi {
    public OkHttpClient setHttpInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }


    @Override
    public Call<List<GetAllCarsDTO>> getAllCars() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://5b90e1cc3ef10a001445d113.mockapi.io/api/")
                .client(setHttpInterceptor())
                .build();
        MockApiService mockApiService = retrofit.create(MockApiService.class);

        return mockApiService.getAllCars();


    }

    @Override
    public Call<List<GetModelInfoDTO>> getModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://5b90e1cc3ef10a001445d113.mockapi.io/api/")
                .client(setHttpInterceptor())
                .build();
        MockApiService mockApiService = retrofit.create(MockApiService.class);
        return mockApiService.getModel();
    }
}
