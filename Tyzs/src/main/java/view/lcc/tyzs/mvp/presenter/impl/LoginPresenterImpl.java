package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

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
    public void login(final String phone, final String password) {
        view.Loading();
        model.onLogin(phone, password, new ResultCallback<String>() {
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
                        String result = (String) jsonObject.get("resultjson");
                        JSONObject con = new JSONObject(result);
                        String nickName = con.getString("name");
                        if (nickName != null) {
                            String UID = con.getString("UID");
                            String cardId = con.getString("cardid");
                            String rate = con.getString("rate");
                            String rName = con.getString("rname");


                            SharePreferenceUtil.setName(phone);
                            SharePreferenceUtil.setNickname(nickName);
                            SharePreferenceUtil.setrName(rName);
                            SharePreferenceUtil.setCardId(cardId);
                            SharePreferenceUtil.setUid(UID);
                            SharePreferenceUtil.setPwd(password);
                           SharePreferenceUtil.setRate(rate);
                        }
                        view.onLoginSuccess(result);
                    } else {
                        view.onLoginFail("登录失败");
                    }
                } catch (Exception e) {
                    view.onLoginFail("登录失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
