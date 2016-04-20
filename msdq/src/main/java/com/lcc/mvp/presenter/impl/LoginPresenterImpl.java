package com.lcc.mvp.presenter.impl;

import android.util.Log;

import com.lcc.App;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.LoginModel;
import com.lcc.mvp.presenter.LoginPresenter;
import com.lcc.mvp.view.LoginView;
import com.squareup.okhttp.Request;

public class LoginPresenterImpl  implements LoginPresenter {

    private LoginView view;
    private LoginModel model;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void login(String phone, String pwd) {
        view.showLoading();
        model.login(phone, pwd, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showLoginFail(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                //将response保存起来
                Log.e("lccx",response);
                view.loginSuccess();
            }
        });
    }
}

