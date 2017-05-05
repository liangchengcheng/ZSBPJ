package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.utils.UserSharePreferenceUtil;

public class FeedBackModel {

    /**
     * 提交用户的反馈意见
     */
    public OkHttpRequest PostMessage(String word, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("author", UserSharePreferenceUtil.getUserPhone());
        paramsMap.put("content_body", "hk:"+word);
        return ApiClient.create(AppConstants.RequestPath.AddFeedBackService, paramsMap).tag("").get(callback);
    }
}
