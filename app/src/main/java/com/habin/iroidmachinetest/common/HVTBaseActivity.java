package com.habin.iroidmachinetest.common;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.habin.iroidmachinetest.R;
import com.habin.iroidmachinetest.common.api.ApiClient;
import com.habin.iroidmachinetest.common.api.ApiInterface;


public class HVTBaseActivity extends AppCompatActivity implements
        NetworkChangeReceiver.Internet {
    OnBackButtonClickedListener backButtonClickedListener;
    public ProgressDialog progressDialog;
    public AppPreferences appPrefes;
    public ConnectionDetector cd;
    public Toast mToast;
    public ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle arg0) {

        // Show status bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(arg0);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        appPrefes = new AppPreferences(this, getResources().getString(R.string.app_name));
        cd = new ConnectionDetector(this);
        NetworkChangeReceiver.internet = this;
        apiInterface= ApiClient.getclient().create(ApiInterface.class);
    }

//    public void addFragment(Fragment fragment, boolean addToBackStack,int transition, String name) {
//		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		ft.add(R.id.container, fragment);
//		ft.setTransition(transition);
//		if (addToBackStack)
//			ft.addToBackStack(name);
//		ft.commit();
//    }


    public void showOneTimeToast(String msg) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void net() {

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (backButtonClickedListener != null)
            backButtonClickedListener.onBackButtonClicked();
        else
            super.onBackPressed();
    }

    public void hidekey() {
        // TODO Auto-generated method stub
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), 0);
    }


    public interface OnBackButtonClickedListener {
        void onBackButtonClicked();
    }

    public void backpress() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }




    public void afterparse(String response, int value) {
        // TODO Auto-generated method stub

    }




    protected boolean checkvalidate(EditText... editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().equals("")) {
                editTexts[i].setError("Error!!");
                return false;
            }
        }
        return true;
    }

    public final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }

    public void showNoInternetAlert(final int apiCode) {
        try {
            final Dialog dialog = new Dialog(this);
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

    public void showServerErrorAlert(final int apiCode) {
        try {
            final Dialog dialog = new Dialog(this);
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

    public void showRequestSuccessDialog(String title, String message, String button, final int code) {
        final Dialog dialog = new Dialog(this);
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
                onSuccessDialogClick(code);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showAlertDialog(String title, String message, String okButton, String cancelButton, final int code) {
        final Dialog dialog = new Dialog(this);
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

    public static boolean IsValidEmail(CharSequence sequence){
        return (!TextUtils.isEmpty(sequence)&& Patterns.EMAIL_ADDRESS.matcher(sequence).matches());
    }

    public void retryApiCall(int apiCode) {

    }

    public void onClickAlertOkButton(int apiCode) {

    }

    public void onSuccessDialogClick(int apiCode) {

    }
}
