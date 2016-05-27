package com.lcc.mvp.presenter.impl;

import com.lcc.db.test.UserInfo;
import com.lcc.entity.ActivityEntity;
import com.lcc.frame.data.DataManager;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.IndexModel;
import com.lcc.mvp.model.LoginModel;
import com.lcc.mvp.presenter.IndexPresenter;
import com.lcc.mvp.presenter.LoginPresenter;
import com.lcc.mvp.view.IndexView;
import com.lcc.mvp.view.LoginView;
import com.lcc.utils.SharePreferenceUtil;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import zsbpj.lccpj.frame.ApiException;
import zsbpj.lccpj.utils.GsonUtils;

public class IndexPresenterImpl implements IndexPresenter {

    private IndexView view;
    private IndexModel model;

    public IndexPresenterImpl(IndexView view) {
        this.view = view;
        model = new IndexModel();
    }

    @Override
    public void getActivity() {
        model.getActivity(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getLoginFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    String result = jsonObject.getString("result");
                    List<ActivityEntity> userInfo = GsonUtils.fromJsonArray(result, ActivityEntity.class);
                    if (status == 1) {
                        view.getSuccess(userInfo);
                    } else {
                        view.getLoginFail(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

