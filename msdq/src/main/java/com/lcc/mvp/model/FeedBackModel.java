package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class FeedBackModel {

    /**
     * 提交用户的反馈意见
     */
    public OkHttpRequest PostMessage(String word, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, word);
        paramsMap.put(AppConstants.ParamKey.GRANT_TYPE_KEY, AppConstants.ParamDefaultValue.GRANT_TYPE);
        // TODO: 16/4/20 改为post
        return ApiClient.create(AppConstants.RequestPath.LOGIN, paramsMap).tag("").get(callback);
    }
}
