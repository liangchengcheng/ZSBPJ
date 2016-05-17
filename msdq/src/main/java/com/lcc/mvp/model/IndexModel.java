package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class IndexModel {

    /**
     * 获取活动数据
     *
     * @param phone 手机号码
     * @param password 密码
     * @param callback 回调函数 默认返回的String 其实可以是其他的javabean
     */
    public OkHttpRequest login(String phone, String password, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        // TODO: 16/4/20 改为post
        return ApiClient.create(AppConstants.RequestPath.GET_ACTIVITY, paramsMap).tag("").get(callback);
    }
}
