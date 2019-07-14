package com.filippovalexandr.productorsapplication.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConfig {
    public static RESTService restMockApi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://5b90e1cc3ef10a001445d113.mockapi.io/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(RESTService.class);
    }
}
