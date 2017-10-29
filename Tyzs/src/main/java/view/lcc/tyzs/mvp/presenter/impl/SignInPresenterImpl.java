package view.lcc.tyzs.mvp.presenter.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.GetOrderModel;
import view.lcc.tyzs.mvp.model.SigninModel;
import view.lcc.tyzs.mvp.presenter.GetOrderPresenter;
import view.lcc.tyzs.mvp.presenter.SignInPresenter;
import view.lcc.tyzs.mvp.view.GetOrderView;
import view.lcc.tyzs.mvp.view.SigninView;
import view.lcc.tyzs.utils.ErrorLogUtils;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.utils.TimeUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |获取订单
 */
public class SignInPresenterImpl implements SignInPresenter {
    private SigninView view;
    private SigninModel model;
    private static final int DEF_DELAY = (int) (1 * 1000);


    public SignInPresenterImpl(SigninView view) {
        this.view = view;
        model = new SigninModel();
    }


    @Override
    public void signIn(String phone) {
        view.onSigninLoading();
        model.signIn(phone, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.onSigninFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("resultno");
                    if (!TextUtils.isEmpty(status) && status.equals("000")) {
                        view.onSigninSuccess();
                    } else {
                        view.onSigninFail(ErrorLogUtils.SystemError(status));
                    }
                } catch (Exception e) {
                    view.onSigninFail("登录失败");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getSignIn(String phone) {

    }
}
