package com.habin.iroidmachinetest.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.habin.iroidmachinetest.R;
import com.habin.iroidmachinetest.common.api.model.HomePageListingModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductGridAdaptor extends RecyclerView.Adapter<ProductGridAdaptor.ViewHolder> {

    private Context context;
    private List<HomePageListingModel.DataBean.FreshProductsBean> freshProductsBeans;

    public ProductGridAdaptor(Context applicationContext, List<HomePageListingModel.DataBean.FreshProductsBean> freshProductsBeans) {
        this.context = applicationContext;
        this.freshProductsBeans = freshProductsBeans;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGridAdaptor.ViewHolder holder, int position) {
        Picasso.get().load(freshProductsBeans.get(position).getImage()).placeholder(R.mipmap.placeholder).into(holder.img_prdt_img);
        holder.tv_prdt_name.setText(freshProductsBeans.get(position).getName());
        if (!freshProductsBeans.get(position).getDiscount().isEmpty()) {
            holder.tv_dis_price.setText(context.getString(R.string.Rs) + "" + freshProductsBeans.get(position).getPrice() + "");
            holder.tv_off.setText(freshProductsBeans.get(position).getDiscount() + "");
            double price = Double.parseDouble(freshProductsBeans.get(position).getPrice()) - Double.parseDouble(freshProductsBeans.get(position).getDiscount());
            holder.tv_price.setText(context.getString(R.string.Rs) + "" + price + " ");
        }else {
            holder.tv_dis_price.setVisibility(View.GONE);
            holder.v_discnt.setVisibility(View.GONE);
            holder.tv_off.setVisibility(View.GONE);
            holder.tv_price.setText(context.getString(R.string.Rs) + "" + freshProductsBeans.get(position).getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return freshProductsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_prdt_img;
        private TextView tv_dis_price, tv_price, tv_prdt_name, tv_off;
        private View v_discnt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_prdt_img = itemView.findViewById(R.id.img_prdt_img);
            tv_dis_price = itemView.findViewById(R.id.tv_dis_price);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_prdt_name = itemView.findViewById(R.id.tv_prdt_name);
            tv_off = itemView.findViewById(R.id.tv_off);
            v_discnt = itemView.findViewById(R.id.v_discnt);
        }
    }
}
