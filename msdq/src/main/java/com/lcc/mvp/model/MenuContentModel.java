package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.Article;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

public class MenuContentModel {

    /**
     * 获取文章的具体内容
     */
    public OkHttpRequest getArticleContent(String id, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.FID, id);
        return ApiClient.create(AppConstants.RequestPath.GET_MENU_CONTENT, paramsMap).tag("").get(callback);
    }

    /**
     * 收藏文章的服务
     */
    public OkHttpRequest favArticle(Article article, String type, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.NID, article.getMid());
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, type);
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
        return ApiClient.create(AppConstants.RequestPath.ARTICLE_DATA, paramsMap).tag("").get(callback);
    }
}
