package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月15日20:03:25
 * Description:  |修改密码
 */
public class ChangePwdModel {

    public OkHttpRequest changePwd(String newpwd,String phone, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("newpwd", newpwd);
        paramsMap.put("phone", phone);
        return ApiClient.create(AppConstants.RequestPath.SIGN, paramsMap).post(callback);
    }

}
