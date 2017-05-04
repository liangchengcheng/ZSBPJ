package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.Register;
import view.lcc.wyzsb.mvp.param.UserName;
import view.lcc.wyzsb.utils.UserSharePreferenceUtil;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  注册昵称
 */
public class UserNameModel {

    public OkHttpRequest signUserName(UserName userName, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("userid", UserSharePreferenceUtil.getUserSession());
        paramsMap.put("phone", UserSharePreferenceUtil.getUserPhone());
        paramsMap.put("nickname", userName.getUsername());
        return ApiClient.create(AppConstants.RequestPath.USERNAME, paramsMap).get(callback);
    }

}
