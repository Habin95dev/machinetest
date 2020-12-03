package com.habin.iroidmachinetest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.habin.iroidmachinetest.R;
import com.habin.iroidmachinetest.adaptor.BannerAdapter;
import com.habin.iroidmachinetest.adaptor.CatagoriesAdaptor;
import com.habin.iroidmachinetest.adaptor.ProductGridAdaptor;
import com.habin.iroidmachinetest.common.Constants;
import com.habin.iroidmachinetest.common.HVTFragmentHelper;
import com.habin.iroidmachinetest.common.api.model.HomePageListingModel;

import java.util.List;

public class HomeFragment extends HVTFragmentHelper {

    private ViewPager vp_banner;
    private HomeViewModel homeViewModel;
    private View root;
    private RelativeLayout rl_banner, rl_catg,rl_new_prdt,rl_prdt;
    private RecyclerView rcv_cat,rcv_new_product,rcv_product;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog.show();
        Init();
        setViewModel();
        OndataChange();
        return root;
    }

    private void Init() {
        vp_banner = root.findViewById(R.id.vp_banner);
        rl_banner = root.findViewById(R.id.rl_banner);
        rl_catg = root.findViewById(R.id.rl_catg);
        rcv_cat = root.findViewById(R.id.rcv_cat);
        rl_new_prdt = root.findViewById(R.id.rl_new_prdt);
        rl_prdt = root.findViewById(R.id.rl_prdt);
        rcv_new_product = root.findViewById(R.id.rcv_new_product);
        rcv_product = root.findViewById(R.id.rcv_product);
    }

    private void setViewModel() {
        if (cd.isConnectingToInternet()) {
            homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        } else {
            progressDialog.dismiss();
            showNoInternetAlert(getContext(), Constants.ApiErrorActionCode);
        }
    }

    private void OndataChange() {
        homeViewModel.getHomeData().observe(getViewLifecycleOwner(), new Observer<HomePageListingModel>() {
            @Override
            public void onChanged(HomePageListingModel homePageListingModel) {


                if (homePageListingModel != null) {
                    if (homePageListingModel.getStatus().equals("success")) {
                        if (!homePageListingModel.getData().getBanners().isEmpty()) {
                            setBannerAdaptor(homePageListingModel.getData().getBanners());
                        }
                        if (!homePageListingModel.getData().getCategories().isEmpty()) {
                            setCatg(homePageListingModel.getData().getCategories());
                        }
                        if (!homePageListingModel.getData().getFresh_products().isEmpty()) {
                            setProduct(homePageListingModel.getData().getFresh_products());
                        }if (!homePageListingModel.getData().getProductsForYou().isEmpty()) {
                            setforyouProduct(homePageListingModel.getData().getProductsForYou());
                        }

                    } else {
                    }

                } else {
                    showServerErrorAlert(getContext(), Constants.ApiErrorActionCode);
                }
                progressDialog.dismiss();
            }
        });
    }

    private void setBannerAdaptor(List<HomePageListingModel.DataBean.BannersBean> banners) {
        rl_banner.setVisibility(View.VISIBLE);
        vp_banner.setAdapter(new BannerAdapter(getActivity(), banners));
    }

    private void setCatg(List<HomePageListingModel.DataBean.CategoriesBean> categories) {
        rl_catg.setVisibility(View.VISIBLE);
        rcv_cat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_cat.setAdapter(new CatagoriesAdaptor(getContext(), categories));
    }
    private void setProduct(List<HomePageListingModel.DataBean.FreshProductsBean> fresh_products){
        rl_new_prdt.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2,GridLayoutManager.HORIZONTAL,false);
        rcv_new_product.setLayoutManager(manager);
        rcv_new_product.setAdapter(new ProductGridAdaptor(getContext(),fresh_products));
    }
    private void setforyouProduct(List<HomePageListingModel.DataBean.FreshProductsBean> fresh_products){
        rl_prdt.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2,GridLayoutManager.HORIZONTAL,false);
        rcv_product.setLayoutManager(manager);
        rcv_product.setAdapter(new ProductGridAdaptor(getContext(),fresh_products));
    }

    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        if (Constants.ApiErrorActionCode == apiCode) {
            setViewModel();
        }
    }
}
