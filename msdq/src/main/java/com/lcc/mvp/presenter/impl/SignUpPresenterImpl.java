package com.lcc.mvp.presenter.impl;

import com.google.gson.JsonElement;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.LoginModel;
import com.lcc.mvp.model.SignUpModel;
import com.lcc.mvp.presenter.SignUpPresenter;
import com.lcc.mvp.view.SignUpView;
import com.squareup.okhttp.Request;

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView view;
    private SignUpModel model;

    public SignUpPresenterImpl(SignUpView view) {
        this.view = view;
        model = new SignUpModel();
    }

    @Override
    public void signUp(String verify_code, final String phone, final String pwd) {
        model.signUp(phone, pwd, verify_code, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showSignUpError(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                view.signUpSuccess();
                autoLogin(phone, pwd);
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {

        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showVerifyError(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                if (response.getAsBoolean()) {
                    view.showVerifySuccess();
                }
            }
        });
    }

    public void autoLogin(String phone, String pwd) {
        new LoginModel().login(phone, pwd, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showMsg("自动登录失败");
            }

            @Override
            public void onResponse(String response) {
                //将response进行一些列的操作 他不是String
                view.showMsg("自动登录成功");
            }
        });

    }
}
