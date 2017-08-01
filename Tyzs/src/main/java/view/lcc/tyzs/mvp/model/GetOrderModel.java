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
 * Description:  获取订单
 */
public class GetOrderModel {

    public OkHttpRequest register(String page,String pagesize,String phone,String state, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("page", page);
        paramsMap.put("pagesize", pagesize);
        paramsMap.put("phone", phone);
        paramsMap.put("state", state);
        return ApiClient.create(AppConstants.RequestPath.SIGN, paramsMap).post(callback);
    }

}
