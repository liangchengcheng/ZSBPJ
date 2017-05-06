package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.RegisterModel;
import view.lcc.wyzsb.mvp.model.UserNameModel;
import view.lcc.wyzsb.mvp.param.Register;
import view.lcc.wyzsb.mvp.param.UserName;
import view.lcc.wyzsb.mvp.presenter.RegisterPresenter;
import view.lcc.wyzsb.mvp.presenter.UserNamePresenter;
import view.lcc.wyzsb.mvp.view.RegisterView;
import view.lcc.wyzsb.mvp.view.UserNameView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年05月05日16:20:12
 * Description:  |
 */
public class UserNamePresenterImpl implements UserNamePresenter {
    private UserNameView view;
    private UserNameModel model;

    public UserNamePresenterImpl(UserNameView view) {
        this.view = view;
        model = new UserNameModel();
    }

    @Override
    public void userName(UserName username) {
        view.UserNameLoading();
        model.signUserName(username, new ResultCallback<String>() {

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
                        view.UserNameSuccess("");
                    } else if (status == 0) {
                        view.UserNameFail(message);
                    }
                } catch (Exception e) {
                    view.UserNameFail("注册昵称");
                    e.printStackTrace();
                }
            }
        });
    }

}
