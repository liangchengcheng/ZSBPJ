package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.Article;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

// TODO: 16/6/14 获取文章的时候动态的获取作者和token
public class MenuContentModel {

    /**
     * 获取文章的具体内容
     */
    public OkHttpRequest getArticleContent(String id, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.FID, id);
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
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
        paramsMap.put("fav_title",article.getTitle());
        return ApiClient.create(AppConstants.RequestPath.UserFavAdd, paramsMap).tag("").get(callback);
    }

    /**
     * 取消收藏文章的服务
     */
    public OkHttpRequest UnfavArticle(Article article, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.NID, article.getMid());
        paramsMap.put(AppConstants.ParamKey.AUTHOR, "18813149871");
        return ApiClient.create(AppConstants.RequestPath.DDELETEFAV, paramsMap).tag("").get(callback);
    }
}
