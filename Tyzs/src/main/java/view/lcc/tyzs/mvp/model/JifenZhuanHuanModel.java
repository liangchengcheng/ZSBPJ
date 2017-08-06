package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.JifenZhuanhuanRequest;
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
public class JifenZhuanHuanModel {

    public OkHttpRequest jifenZhuanHuan(
                                     String value,  ResultCallback<String> callback) {
        JifenZhuanhuanRequest jifenZhuanhuanRequest = new JifenZhuanhuanRequest();
        jifenZhuanhuanRequest.setPhone( SharePreferenceUtil.getName());
        jifenZhuanhuanRequest.setValue( value);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(jifenZhuanhuanRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.JFZH;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
