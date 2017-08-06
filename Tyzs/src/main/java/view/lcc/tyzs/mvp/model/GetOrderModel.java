package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.GetOrderRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  获取订单
 */
public class GetOrderModel {

    public OkHttpRequest register(String page, String pagesize, String phone, String state, ResultCallback<String> callback) {
        GetOrderRequest getOrderRequest = new GetOrderRequest();
        getOrderRequest.setPhone(phone);
        getOrderRequest.setPage(page);
        getOrderRequest.setPagesize(pagesize);
        getOrderRequest.setState(state);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(getOrderRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.GET_ORDER;
        if (url.endsWith("ashx")) {
            url = url + "?Calldate=" + timeValue;
        } else {
            url = url + "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
