package view.lcc.wyzsb.mvp.presenter.impl;


import com.squareup.okhttp.Request;
import org.json.JSONException;
import org.json.JSONObject;
import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.CheckCodeModel;
import view.lcc.wyzsb.mvp.param.CheckVcode;
import view.lcc.wyzsb.mvp.presenter.CheckCodePresenter;
import view.lcc.wyzsb.mvp.view.CheckCodeView;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  校验验证码
 */
public class CheckCodePresenterImpl implements CheckCodePresenter {
    private CheckCodeView view;
    private CheckCodeModel model;

    public CheckCodePresenterImpl(CheckCodeView view) {
        this.view = view;
        model = new CheckCodeModel();
    }

    @Override
    public void checkVCode(CheckVcode vCode) {
        view.onCheckLoading();
        model.checkVCode(vCode, new ResultCallback<String>() {

            @Override
            public void onError(Request request, Exception e) {
                view.onCheckNetworkError(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("status");
                    if (code == 1) {
                        String result = jsonObject.getString(AppConstants.RESPONSE_RESULT);
                        if (result != null){
                            view.onVcodeCheckSuccess(result);
                        }else {
                            view.onVcodeCheckFail("校验验证码程序异常，请稍后");
                        }
                    } else if (code == 0) {
                        view.onVcodeCheckFail(jsonObject.getString(AppConstants.RESPONSE_MSG));
                    }
                } catch (JSONException e) {
                    view.onVcodeCheckFail("验证码校验失败");
                }
            }
        });
    }
}
