package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  注册
 */
public class RegisterModel {

    public OkHttpRequest register(String phone,String name,String cardid,String Pwd, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", phone);
        paramsMap.put("name", name);
        paramsMap.put("cardid", cardid);
        paramsMap.put("Pwd", Pwd);
        return ApiClient.create(AppConstants.RequestPath.SIGN, paramsMap).post(callback);
    }

}
