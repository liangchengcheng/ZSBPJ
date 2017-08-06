package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.ChangePwdRequest;
import view.lcc.tyzs.bean.request.LoginRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月15日20:03:25
 * Description:  |修改密码
 */
public class ChangePwdModel {

    public OkHttpRequest changePwd(String newpwd,String phone, ResultCallback<String> callback) {
        ChangePwdRequest changePwdRequest = new ChangePwdRequest();
        changePwdRequest.setPhone(phone);
        changePwdRequest.setNewpwd(Md5Utils.encode(newpwd).toUpperCase());

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(changePwdRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.CHANGEPWD;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
