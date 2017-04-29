package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.CheckVcode;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2016年10月19日13:36:27
 * Description:  校验验证码
 */
public class CheckCodeModel {
    public OkHttpRequest checkVCode(CheckVcode checkVcode, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", checkVcode.getPhone());
        paramsMap.put("code", checkVcode.getCode());
        return ApiClient.createUser(AppConstants.RequestPath.CHECK_VCODE, paramsMap).get(callback);
    }

}
