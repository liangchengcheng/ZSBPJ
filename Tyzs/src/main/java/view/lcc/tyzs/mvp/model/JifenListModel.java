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
public class JifenListModel {

    public OkHttpRequest jifenList(String page,String type, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("page", page);
        paramsMap.put("user", SharePreferenceUtil.getName());
        paramsMap.put("bs", "OR");
        paramsMap.put("pagesize", "10");
        paramsMap.put("type", type);
        return ApiClient.create(AppConstants.RequestPath.JFLB, paramsMap).get(callback);
    }

}
