package view.lcc.wyzsb.mvp.model;

import java.util.HashMap;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.ArticleParams;
import view.lcc.wyzsb.mvp.param.HomeParams;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class ArticleModel {

    /**
     * 获取我的订单详情
     */
    public OkHttpRequest getArticle(ArticleParams articleParams, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("page", "1");
        paramsMap.put("company_name", "");
        paramsMap.put("area", "");
        return ApiClient.create(AppConstants.RequestPath.GET_ARTICLE, paramsMap).tag("").get(callback);
    }
}