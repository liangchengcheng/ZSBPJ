package com.lcc.mvp.model;

import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.TestEntity;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;
import java.util.List;

public class TestModel {

    public OkHttpRequest getTestList(int page,String options,String startTime,String endTime, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put("options", options);
        paramsMap.put("start", startTime);
        paramsMap.put("end", endTime);
        return ApiClient.create(AppConstants.RequestPath.TEST_LIST, paramsMap).tag("").get(callback);
    }
}
