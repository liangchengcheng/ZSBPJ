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
 * Description:  |获取自己的下级人数
 */
public class PersonNumGetModel {

    public OkHttpRequest PersonNumGet(String phone, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", phone);
        return ApiClient.create(AppConstants.RequestPath.SHOP_CAR_GET, paramsMap).get(callback);
    }

}
