package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.JifenListRequest;
import view.lcc.tyzs.bean.request.LoginRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:
 */
public class JifenListModel {

    public OkHttpRequest jifenList(String page,String type, ResultCallback<String> callback) {
        JifenListRequest jifenListRequest = new JifenListRequest();
        jifenListRequest.setPage(page);
        jifenListRequest.setUser( SharePreferenceUtil.getName());
        jifenListRequest.setBs("OR");
        jifenListRequest.setPagesize("10");
        jifenListRequest.setType(type);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(jifenListRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.JFLB;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
