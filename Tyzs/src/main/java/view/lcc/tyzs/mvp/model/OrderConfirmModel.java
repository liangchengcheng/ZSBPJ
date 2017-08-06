package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.OrderConfirmRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:
 */
public class OrderConfirmModel {

    public OkHttpRequest orderConfirm(String verification, String content, ResultCallback<String> callback) {
        OrderConfirmRequest orderConfirmRequest = new OrderConfirmRequest();
        orderConfirmRequest.setContent(content);
        orderConfirmRequest.setVerification(verification);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(orderConfirmRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.LOGIN;
        if (url.endsWith("ashx")) {
            url = url + "?Calldate=" + timeValue;
        } else {
            url = url + "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
