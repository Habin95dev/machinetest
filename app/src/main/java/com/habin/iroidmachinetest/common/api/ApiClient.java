package com.habin.iroidmachinetest.common.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.habin.iroidmachinetest.common.Constants;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit =null;
    private static Gson gson =new GsonBuilder()
            .setLenient()
            .create();
    static OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(1,TimeUnit.MINUTES)
            .writeTimeout(1,TimeUnit.MINUTES)
            .build();

    public static Retrofit getclient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
