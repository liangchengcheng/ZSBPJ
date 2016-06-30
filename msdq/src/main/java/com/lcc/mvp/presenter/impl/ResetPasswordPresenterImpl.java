package com.lcc.mvp.presenter.impl;

import com.google.gson.JsonElement;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.ResetPasswordModel;
import com.lcc.mvp.presenter.ResetPasswordPresenter;
import com.lcc.mvp.view.ResetPasswordView;
import com.squareup.okhttp.Request;

public class ResetPasswordPresenterImpl implements ResetPasswordPresenter {

    private ResetPasswordView view;
    private ResetPasswordModel model;

    public ResetPasswordPresenterImpl(ResetPasswordView view) {
        this.view = view;
        this.model = new ResetPasswordModel();
    }

    @Override
    public void resetPassword(String verify_code, String phone, String pwd,String new_pwd) {
        model.resetPassword(verify_code,phone, pwd, new_pwd,new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showResetError(e.getMessage());

            }

            @Override
            public void onResponse(String response) {
                view.showSuccess();
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {
        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showMsg(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                view.showSmsSuccess();
            }
        });
    }
}
