package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:
 */
public class ShenqingtuihuoModel {

    public OkHttpRequest Shenqingtuihuo(String OID,String Reason, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("user", SharePreferenceUtil.getName());
        paramsMap.put("OID", OID);
        paramsMap.put("Reason", Reason);
        return ApiClient.create(AppConstants.RequestPath.SHOP_CAR_ADD, paramsMap).post(callback);
    }

}
