package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class LatterEntityModel {

    /**
     * 获取自己的私信
     */
    public OkHttpRequest getLetterByPhone(String page,String from, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.AUTHOR , "18813149871");
        paramsMap.put("from" , from);
        paramsMap.put("page" , page);
        paramsMap.put("to" , "18813149871");
        return ApiClient.create(AppConstants.RequestPath.getLetterByPhone, paramsMap).tag("")
                .get(callback);
    }
}
