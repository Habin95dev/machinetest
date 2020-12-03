package com.habin.iroidmachinetest.common;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.habin.iroidmachinetest.R;


//....Class to change fragments....//
public class HVTFragmentHelper extends Fragment implements
        NetworkChangeReceiver.Internet{

    public ConnectionDetector cd;
    public AppPreferences appPrefes;
    public ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPrefes = new AppPreferences(getContext(), getResources().getString(R.string.app_name));
        cd = new ConnectionDetector(getContext());
        NetworkChangeReceiver.internet = this;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
    }







    public void addFragment(Fragment fragment, boolean addToBackStack,
                            int transition, String name, Context context) {
//
//		FragmentTransaction ft = ((FragmentActivity) context)
//				.getSupportFragmentManager().beginTransaction();
//		ft.add(R.id.container, fragment);
//		ft.setTransition(transition);
//		if (addToBackStack)
//			ft.addToBackStack(name);
//		ft.commit();
    }


    public void replaceFragment(Fragment fragment, boolean addToBackStack,
                                int transition, String name, Context context) {

//		FragmentTransaction ft = ((FragmentActivity) context)
//				.getSupportFragmentManager().beginTransaction();
//		ft.replace(R.id.container, fragment);
//		ft.setTransition(transition);
//		if (addToBackStack)
//			ft.addToBackStack(name);
//		ft.commit();
    }







    public void showNoInternetAlert(Context context, final int apiCode) {
        try {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_no_internet);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            final TextView tvRetry = dialog.findViewById(R.id.tvRetry);
            final TextView tvClose = dialog.findViewById(R.id.tvClose);
            tvRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    retryApiCall(apiCode);
                }
            });
            tvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServerErrorAlert(Context context, final int apiCode) {
        try {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_server_error);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            final TextView tvRetry = dialog.findViewById(R.id.tvRetry);
            final TextView tvClose = dialog.findViewById(R.id.tvClose);
            tvRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    retryApiCall(apiCode);
                }
            });
            tvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRequestSuccessDialog(Context context, String title, String message, String button) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_popup);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        final TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        final TextView tvCancel = dialog.findViewById(R.id.tvCancel);
        tvTitle.setText(title);
        tvMessage.setText(message);
        tvCancel.setText(button);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showAlertDialog(Context context, String title, String message, String okButton, String cancelButton, final int code) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_alert);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        final TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        final TextView tvOk = dialog.findViewById(R.id.tvOk);
        final TextView tvCancel = dialog.findViewById(R.id.tvCancel);
        tvTitle.setText(title);
        tvMessage.setText(message);
        tvOk.setText(okButton);
        tvCancel.setText(cancelButton);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAlertOkButton(code);
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void retryApiCall(int apiCode) {

    }

    public void onClickAlertOkButton(int apiCode) {

    }

    @Override
    public void net() {

    }
}
