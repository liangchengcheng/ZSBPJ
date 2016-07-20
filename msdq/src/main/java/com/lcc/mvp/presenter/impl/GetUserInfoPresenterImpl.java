package com.lcc.mvp.presenter.impl;

import android.os.Handler;

import com.lcc.entity.FavEntity;
import com.lcc.entity.NewsInfo;
import com.lcc.entity.otherUserInfo;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.FavModel;
import com.lcc.mvp.model.GetUserInfoModel;
import com.lcc.mvp.presenter.FavPresenter;
import com.lcc.mvp.presenter.GetUserInfoPresenter;
import com.lcc.mvp.view.FavView;
import com.lcc.mvp.view.GetUserInfoView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import zsbpj.lccpj.frame.ApiException;
import zsbpj.lccpj.utils.GsonUtils;
import zsbpj.lccpj.utils.TimeUtils;

public class GetUserInfoPresenterImpl implements GetUserInfoPresenter {

    private GetUserInfoView view;
    private GetUserInfoModel model;

    public GetUserInfoPresenterImpl(GetUserInfoView view) {
        this.view = view;
        model = new GetUserInfoModel();
    }


    @Override
    public void getData(String phone) {
        view.getLoading();
        model.getUserInfo(phone,new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        String result = jsonObject.getString("result");
                        otherUserInfo userInfo = GsonUtils.changeGsonToBean(result, otherUserInfo.class);
                        view.getDataSuccess(userInfo);
                    } else {
                        view.getDataFail(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

