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
public class JifenZhuanZhangModel {

    public OkHttpRequest jifenZhuanZhangModel(String aphone,
                                     String value,  ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("phone", SharePreferenceUtil.getName());
        paramsMap.put("aphone", aphone);
        paramsMap.put("value", value);
        return ApiClient.create(AppConstants.RequestPath.JFZZ, paramsMap).get(callback);
    }

}
