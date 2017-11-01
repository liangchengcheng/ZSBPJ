package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.ChangePwdModel;
import view.lcc.tyzs.mvp.model.RegisterModel;
import view.lcc.tyzs.mvp.presenter.ChangePwdPresenter;
import view.lcc.tyzs.mvp.presenter.RegisterPresenter;
import view.lcc.tyzs.mvp.view.ChangePwdView;
import view.lcc.tyzs.mvp.view.RegisterView;
import view.lcc.tyzs.utils.ErrorLogUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |注册接口
 */
public class ChangePwdPresenterImpl implements ChangePwdPresenter {
    private ChangePwdView view;
    private ChangePwdModel model;

    public ChangePwdPresenterImpl(ChangePwdView view) {
        this.view = view;
        model = new ChangePwdModel();
    }

    @Override
    public void changePwd(String newpwd,String phone) {
        view.ChangePwdLoading();
        model.changePwd(newpwd,phone, new ResultCallback<String>() {

            @Override
            public void onError(Request request, Exception e) {
                view.NetWorkErr(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("resultno");
                    if (!TextUtils.isEmpty(status) && status.equals("000")) {
                        view.ChangePwdSuccess("");
                    } else  {
                        view.ChangePwdFail(ErrorLogUtils.SystemError(status));
                    }
                } catch (Exception e) {
                    view.ChangePwdFail("修改密码失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
