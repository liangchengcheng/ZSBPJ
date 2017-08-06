package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.LoginRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  登录
 */
public class LoginModel {

    public OkHttpRequest onLogin(String phone,String password ,ResultCallback<String> callback) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(phone);
        loginRequest.setPwd( Md5Utils.encode(password).toUpperCase());

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(loginRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.LOGIN;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
