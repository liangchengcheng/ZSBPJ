package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;
import org.json.JSONException;
import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.SendCodeModel;
import view.lcc.wyzsb.mvp.param.SendVcode;
import view.lcc.wyzsb.mvp.presenter.SendCodePresenter;
import view.lcc.wyzsb.mvp.view.SendCodeView;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2016年11月17日10:46:34
 * Description:  发送手机验证码
 */
public class SendCodePresenterImpl implements SendCodePresenter {
    private SendCodeView view;
    private SendCodeModel model;

    public SendCodePresenterImpl(SendCodeView view) {
        this.view = view;
        model = new SendCodeModel();
    }

    @Override
    public void requestVCode(SendVcode phone) {
        view.onSendLoading();
        model.requestVCode(phone, new ResultCallback<String>() {

            @Override
            public void onError(Request request, Exception e) {
                view.onRequestVcodeFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt(AppConstants.RESPONSE_CODE);
                    if (code == 1)
                        view.onRequestVcodeSuccess();
                    else if (code == 0)
                        view.onRequestVcodeFail(jsonObject.getString(AppConstants.RESPONSE_MSG));
                } catch (JSONException e) {
                    view.onRequestVcodeFail("验证码发送失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
