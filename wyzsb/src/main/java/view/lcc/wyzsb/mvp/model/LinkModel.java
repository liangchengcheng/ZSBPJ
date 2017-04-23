package view.lcc.wyzsb.mvp.model;

import java.util.HashMap;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.NewsParams;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class LinkModel {

    /**
     * 获取blog
     */
    public OkHttpRequest getLink(NewsParams newsParams, ResultCallback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",newsParams.getPage()+"");
        return ApiClient.create(AppConstants.RequestPath.GET_LINKS, map).tag("").get(callback);
    }
}
