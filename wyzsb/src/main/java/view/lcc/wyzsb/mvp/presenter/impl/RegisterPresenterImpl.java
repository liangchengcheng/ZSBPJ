package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.LoginModel;
import view.lcc.wyzsb.mvp.model.RegisterModel;
import view.lcc.wyzsb.mvp.param.Login;
import view.lcc.wyzsb.mvp.param.Register;
import view.lcc.wyzsb.mvp.presenter.LoginPresenter;
import view.lcc.wyzsb.mvp.presenter.RegisterPresenter;
import view.lcc.wyzsb.mvp.view.LoginView;
import view.lcc.wyzsb.mvp.view.RegisterView;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:  登录接口
 */
public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView view;
    private RegisterModel model;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        model = new RegisterModel();
    }

    @Override
    public void register(Register register) {
        view.RegisterLoading();
        model.signIn(register, new ResultCallback<String>() {

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
                        view.RegisterSuccess(result);
                    } else if (status == 0) {
                        view.RegisterFail(message);
                    }
                } catch (Exception e) {
                    view.RegisterFail("注册失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
