package com.lcc.mvp.model;

import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.TestEntity;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;
import java.util.List;

public class TestModel {
    public OkHttpRequest getTestList(int id, int type, int page, int count, ResultCallback<List<TestEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, type);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.COUNT_KEY, count);
        return ApiClient.create(AppConstants.RequestPath.TEST_LIST, paramsMap).tag("").get(callback);
    }
}
