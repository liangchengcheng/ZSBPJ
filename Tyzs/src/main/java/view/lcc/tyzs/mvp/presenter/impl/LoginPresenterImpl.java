package view.lcc.tyzs.mvp.presenter.impl;

import com.squareup.okhttp.Request;
import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.LoginModel;
import view.lcc.tyzs.mvp.presenter.LoginPresenter;
import view.lcc.tyzs.mvp.view.LoginView;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:  登录接口
 */
public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private LoginModel model;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void login(String phone,String password) {
        view.Loading();
        model.signIn(phone,password, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.NetWorkErr(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        String result = jsonObject.getString("result");
                        JSONObject ret = new JSONObject(result);

                        SharePreferenceUtil.setUpdateTime(ret.getString("u_i"));
                        view.onSignInSuccess(result);
                    } else if (status == 0) {
                        view.onSignInFail(message);
                    }
                } catch (Exception e) {
                    view.onSignInFail("登录失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
