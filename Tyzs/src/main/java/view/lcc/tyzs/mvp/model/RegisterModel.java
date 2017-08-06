package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.RegisterRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  注册
 */
public class RegisterModel {

    public OkHttpRequest register(String phone, String name, String cardid, String Pwd, ResultCallback<String> callback) {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPhone(phone);
        registerRequest.setPwd(Md5Utils.encode(Pwd).toUpperCase());
        registerRequest.setName(name);
        registerRequest.setCardid(cardid);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(registerRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.SIGN;
        if (url.endsWith("ashx")) {
            url = url + "?Calldate=" + timeValue;
        } else {
            url = url + "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);

    }

}
