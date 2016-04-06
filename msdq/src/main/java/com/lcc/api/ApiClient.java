package com.lcc.api;

import com.lcc.AppConstants;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;
import java.util.Map;

public class ApiClient {

    public static OkHttpRequest.Builder create(String path, Map paramsMap, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.RequestPath.BASE_URL + path).params(paramsMap).headers(headerMap);
    }

    public static OkHttpRequest.Builder create(String path, Map paramsMap) {
        return create(path, paramsMap, null);
    }

    public static OkHttpRequest.Builder create(String path) {
        return create(path, new ParamsMap());
    }

}
