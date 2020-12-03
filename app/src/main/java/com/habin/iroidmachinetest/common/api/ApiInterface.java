package com.habin.iroidmachinetest.common.api;

import com.habin.iroidmachinetest.common.api.model.HomePageListingModel;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("index.php?route=api/common")
    Call<HomePageListingModel>gethomePageListing();


}
