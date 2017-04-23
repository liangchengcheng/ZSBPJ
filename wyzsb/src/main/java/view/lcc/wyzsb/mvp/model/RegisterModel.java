package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.Login;
import view.lcc.wyzsb.mvp.param.Register;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  登录
 */
public class RegisterModel {

    public OkHttpRequest signIn(Register password, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, "");
        return ApiClient.createUser(AppConstants.RequestPath.SIGN, paramsMap)
                .addHeader("phone","").tag("").post(callback);
    }

}
