package com.lcc.mvp.model;

import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.Answer;
import com.lcc.entity.CompanyAnswer;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class ComAnswerContentModel {

    public OkHttpRequest isfavAnswer(String nid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.NID, nid);
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
        return ApiClient.create(AppConstants.RequestPath.ISFAV, paramsMap).tag("").get(callback);
    }
    /**
     * 收藏的公司资料的问题
     */
    public OkHttpRequest favAnswer(CompanyAnswer article, String type, String title,
                                   ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.NID, article.getMid());
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, type);
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
        paramsMap.put("fav_title",title);
        return ApiClient.create(AppConstants.RequestPath.UserFavAdd, paramsMap).tag("").get(callback);
    }

    /**
     * 取消收藏的公司资料的问题
     */
    public OkHttpRequest UnfavAnswer(CompanyAnswer article, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.NID, article.getMid());
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
        return ApiClient.create(AppConstants.RequestPath.DDELETEFAV, paramsMap).tag("").get(callback);
    }

    /**
     * 获取公司资料的答案的具体内容
     */
    public OkHttpRequest getContent(String mid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        // TODO: 16/7/29 此处记得修改时间
        paramsMap.put("mid", mid);
        return ApiClient.create(AppConstants.RequestPath.getComAnswerContent, paramsMap).tag("")
                .get(callback);
    }
}
