package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.SendVcode;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2016年10月19日13:36:27
 * Description:  请求发送验证码
 */
public class SendCodeModel {
    public OkHttpRequest requestVCode(SendVcode phone, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", "");
        return ApiClient.createUser(AppConstants.RequestPath.SIGN, paramsMap).tag("").get(callback);
    }
}
