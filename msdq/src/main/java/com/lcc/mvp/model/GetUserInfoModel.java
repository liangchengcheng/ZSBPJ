package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class GetUserInfoModel {

    /**
     * 获取自己的收藏的列表
     */
    public OkHttpRequest getUserInfo(String phone, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE, phone);
        return ApiClient.create(AppConstants.RequestPath.getUserInfo, paramsMap).tag("").get(callback);
    }
}
