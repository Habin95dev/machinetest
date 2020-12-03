package com.habin.iroidmachinetest.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.habin.iroidmachinetest.R;
import com.habin.iroidmachinetest.common.api.model.HomePageListingModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CatagoriesAdaptor extends RecyclerView.Adapter<CatagoriesAdaptor.ViewHolder> {

    private Context context;
    private List<HomePageListingModel.DataBean.CategoriesBean> categories;
    public CatagoriesAdaptor(Context context, List<HomePageListingModel.DataBean.CategoriesBean> categories) {
        this.context=context;
        this.categories=categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.catg_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoriesAdaptor.ViewHolder holder, int position) {
        holder.tv_prdtcat_name.setText(categories.get(position).getName());
        Picasso.get().load(categories.get(position).getImage()).placeholder(R.mipmap.placeholder).into(holder.img_prdt_img);




    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_prdt_img;
        private TextView tv_prdtcat_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_prdt_img=itemView.findViewById(R.id.img_prdtcat_img);
            tv_prdtcat_name=itemView.findViewById(R.id.tv_prdtcat_name);
        }
    }
}
