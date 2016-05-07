package com.lcc.mvp.presenter.impl;

import android.util.Log;

import com.google.gson.JsonElement;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.LoginModel;
import com.lcc.mvp.model.SignUpModel;
import com.lcc.mvp.presenter.SignUpPresenter;
import com.lcc.mvp.view.SignUpView;
import com.squareup.okhttp.Request;

import zsbpj.lccpj.utils.LogUtils;

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView view;
    private SignUpModel model;

    public SignUpPresenterImpl(SignUpView view) {
        this.view = view;
        model = new SignUpModel();
    }

    @Override
    public void signUp(String phone, String pwd,String verify_code ,String username) {
        model.signUp(username,phone, pwd, verify_code, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtils.e("msb",e);
                view.showSignUpError(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtils.e("msb",response);
                view.signUpSuccess();
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {

        model.getVerifySMS(phone, pwd, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showVerifyError(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                view.showVerifySuccess();
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
