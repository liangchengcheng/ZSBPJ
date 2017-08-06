package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.CarAddRequest;
import view.lcc.tyzs.bean.request.LoginRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:
 */
public class ShopCarAddModel {

    public OkHttpRequest shopCarAdd(String user,String content, ResultCallback<String> callback) {
        CarAddRequest carAddRequest = new CarAddRequest();
        carAddRequest.setUser(user);
        carAddRequest.setContent(content);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(carAddRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.SHOP_CAR_ADD;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
