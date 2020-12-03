package com.habin.iroidmachinetest.adaptor;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.habin.iroidmachinetest.R;
import com.habin.iroidmachinetest.common.api.model.HomePageListingModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BannerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<HomePageListingModel.DataBean.BannersBean> bannersList;

    public BannerAdapter(Context context, List<HomePageListingModel.DataBean.BannersBean> banners) {
        this.context = context;
        this.bannersList=banners;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return bannersList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.banner_item, view, false);
        view.addView(imageLayout, 0);
        ImageView img_banner=imageLayout.findViewById(R.id.img_banner);
        Picasso.get().load(bannersList.get(position).getImage()).placeholder(R.mipmap.placeholder).into(img_banner);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
