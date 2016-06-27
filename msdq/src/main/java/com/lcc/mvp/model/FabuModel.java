package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class FabuModel {

    /**
     * 获取我发布的面试资料的列表
     */
    public OkHttpRequest getTestList(int page,String type, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, type);
        paramsMap.put(AppConstants.ParamKey.AUTHOR , "18813149871");
        return ApiClient.create(AppConstants.RequestPath.getUserFavList, paramsMap).tag("").get(callback);
    }

    /**
     * 获取我发布的公司真题的列表
     */
    public OkHttpRequest getComList(int page,String type, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, type);
        paramsMap.put(AppConstants.ParamKey.AUTHOR , "18813149871");
        return ApiClient.create(AppConstants.RequestPath.getUserFavList, paramsMap).tag("").get(callback);
    }
}
