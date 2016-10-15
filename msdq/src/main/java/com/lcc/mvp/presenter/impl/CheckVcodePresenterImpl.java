package com.lcc.mvp.presenter.impl;

import android.util.Log;

import com.lcc.db.test.UserInfo;
import com.lcc.frame.data.DataManager;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.CheckVcodeModel;
import com.lcc.mvp.model.LoginModel;
import com.lcc.mvp.model.SignUpModel;
import com.lcc.mvp.presenter.CheckVcodePresenter;
import com.lcc.mvp.presenter.SignUpPresenter;
import com.lcc.mvp.view.CheckVcodeView;
import com.lcc.mvp.view.SignUpView;
import com.lcc.utils.SharePreferenceUtil;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import zsbpj.lccpj.utils.GsonUtils;

public class CheckVcodePresenterImpl implements CheckVcodePresenter {

    private CheckVcodeView view;
    private CheckVcodeModel model;

    public CheckVcodePresenterImpl(CheckVcodeView view) {
        this.view = view;
        model = new CheckVcodeModel();
    }

    @Override
    public void checkVerifySMS(String phone,String verify_code) {
        model.checkVerifySMS(phone, verify_code, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.CheckVerifyCodeError(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                try {
                    Log.e("1","1");
                    Log.e("1","1");
 //                   JSONObject jsonObject = new JSONObject(response);
//                    int status = jsonObject.getInt("status");
//                    String message = jsonObject.getString("message");
//
//                    if (status == 1) {
//                        String result = jsonObject.getString("result");
//                        JSONObject json_result = new JSONObject(result);
//                        SharePreferenceUtil.setUserTk(json_result.getString("tk"));
//                        String user_info=json_result.getString("userinfo");
//                        UserInfo userInfo = GsonUtils.changeGsonToBean(user_info, UserInfo.class);
//                        DataManager.saveUserInfo(userInfo);
//                        view.signUpSuccess();
//                    } else {
//                        view.CheckVerifyCodeError(message);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
