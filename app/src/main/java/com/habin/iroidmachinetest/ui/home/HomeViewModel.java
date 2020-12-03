package com.habin.iroidmachinetest.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.habin.iroidmachinetest.common.api.ApiClient;
import com.habin.iroidmachinetest.common.api.ApiInterface;
import com.habin.iroidmachinetest.common.api.model.HomePageListingModel;

import retrofit2.Call;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<HomePageListingModel> mutableLiveData=new MutableLiveData<>();
    private LiveData<HomePageListingModel> liveData=mutableLiveData;

    public HomeViewModel() {

      HomeListingAPI();
    }

    public LiveData<HomePageListingModel> getHomeData() {
        return liveData;
    }


    public void HomeListingAPI() {
        ApiInterface apiService = ApiClient.getclient().create(ApiInterface.class);
        Call<HomePageListingModel> call = apiService.gethomePageListing();
        call.enqueue(new retrofit2.Callback<HomePageListingModel>() {
            @Override
            public void onResponse(Call<HomePageListingModel> call, Response<HomePageListingModel> response) {
                try {
                    mutableLiveData.setValue(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<HomePageListingModel> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

    }
}