package view.lcc.wyzsb.mvp.model;

import java.util.HashMap;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.CollectionParams;
import view.lcc.wyzsb.mvp.param.HistoryParams;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  获取我的收藏的界面
 */
public class HistoryModel {

    /**
     * 获取我的订单详情
     */
    public OkHttpRequest getHistory(HistoryParams newsParams, ResultCallback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",newsParams.getPage()+"");
        return ApiClient.create(AppConstants.RequestPath.GET_BOOKS, map).tag("").get(callback);
    }
}
