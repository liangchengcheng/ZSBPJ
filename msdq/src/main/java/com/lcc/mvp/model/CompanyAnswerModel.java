package com.lcc.mvp.model;

import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class CompanyAnswerModel {
    public OkHttpRequest getCompanyAnswer(int page,String fid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.FID, fid);
        return ApiClient.create(AppConstants.RequestPath.COMPANY_ANSWER_LIST, paramsMap).tag("").get(callback);
    }
}
