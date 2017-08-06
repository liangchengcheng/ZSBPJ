package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.JifenZhuanzhangRequest;
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
public class JifenZhuanZhangModel {

    public OkHttpRequest jifenZhuanZhangModel(String aphone,
                                     String value,  ResultCallback<String> callback) {
        JifenZhuanzhangRequest jifenZhuanzhangRequest = new JifenZhuanzhangRequest();
        jifenZhuanzhangRequest.setPhone(SharePreferenceUtil.getName());
        jifenZhuanzhangRequest.setAphone(aphone);
        jifenZhuanzhangRequest.setValue(value);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(jifenZhuanzhangRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.JFZZ;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
