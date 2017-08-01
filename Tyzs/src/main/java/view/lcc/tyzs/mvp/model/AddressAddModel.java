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
 * Description:  获取我的地址
 */
public class AddressAddModel {

    public OkHttpRequest addressAdd(String phone,String address,
                                    String addressee,String aphone, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", phone);
        paramsMap.put("address", address);
        paramsMap.put("addressee", addressee);
        paramsMap.put("aphone", aphone);
        return ApiClient.create(AppConstants.RequestPath.SIGN, paramsMap).post(callback);
    }

}
