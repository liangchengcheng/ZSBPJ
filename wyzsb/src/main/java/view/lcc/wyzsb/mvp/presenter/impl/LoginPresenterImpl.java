package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;
import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.LoginModel;
import view.lcc.wyzsb.mvp.param.Login;
import view.lcc.wyzsb.mvp.presenter.LoginPresenter;
import view.lcc.wyzsb.mvp.view.LoginView;

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
    public void signIn(Login phone) {
        view.Loading();
        model.signIn(phone, new ResultCallback<String>() {

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
                        String uid = ret.getString("uid");
                        String token = ret.getString("token");
                        // TODO: 2017/4/25 这里保存用户名和密码
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
