package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.ArticleParams;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2015年11月21日15:28:25
 * Description:
 */
public class ArticleModel {

    /**
     * 获取文章的列表。
     */
    public OkHttpRequest getArticle(ArticleParams articleParams, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("page",articleParams.getPage());
        paramsMap.put("a_type",articleParams.getA_type());
        paramsMap.put("a_c",articleParams.getA_c());
        paramsMap.put("a_l",articleParams.getA_l());
        return ApiClient.create(AppConstants.RequestPath.GET_ARTICLE, paramsMap).tag("").get(callback);
    }
}
