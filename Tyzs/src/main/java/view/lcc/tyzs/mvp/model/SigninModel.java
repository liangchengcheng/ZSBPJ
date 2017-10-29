package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.GetOrderRequest;
import view.lcc.tyzs.bean.request.SigninRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  获取订单
 */
public class SigninModel {

    public OkHttpRequest signIn( String phone, ResultCallback<String> callback) {
        SigninRequest getOrderRequest = new SigninRequest();
        getOrderRequest.setPhone(phone);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(getOrderRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.QIANDAO;
        if (url.endsWith("ashx")) {
            url = url + "?Calldate=" + timeValue;
        } else {
            url = url + "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

    public OkHttpRequest getSignin( String phone, ResultCallback<String> callback) {
        SigninRequest getOrderRequest = new SigninRequest();
        getOrderRequest.setPhone(phone);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(getOrderRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.QIANDAO;
        if (url.endsWith("ashx")) {
            url = url + "?Calldate=" + timeValue;
        } else {
            url = url + "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
