package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;

public class NewsDetailsModel {

    public OkHttpRequest getContent(String mid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("id", mid);
        return ApiClient.create(AppConstants.RequestPath.getHNewsBody, paramsMap).tag("").get(callback);
    }

}
