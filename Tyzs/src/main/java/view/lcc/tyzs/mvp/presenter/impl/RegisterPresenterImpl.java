package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;
import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.RegisterModel;
import view.lcc.tyzs.mvp.presenter.RegisterPresenter;
import view.lcc.tyzs.mvp.view.RegisterView;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |注册接口
 */
public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView view;
    private RegisterModel model;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        model = new RegisterModel();
    }

    @Override
    public void register(String phone,String name,String cardid,String Pwd) {
        view.RegisterLoading();
        model.register(phone,name,cardid,Pwd, new ResultCallback<String>() {

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
                        view.RegisterSuccess(response);
                    } else  {
                        view.RegisterFail("注册失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.RegisterFail("注册失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
