package view.lcc.wyzsb.mvp.model;

import java.util.HashMap;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.Replay;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class CommentsModel {

    /**
     * 获取评论的列表
     */
    public OkHttpRequest getComments(int page, String nid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("page", page);
        paramsMap.put("nid", nid);
        HashMap<String, String> map = new HashMap<>();
        return ApiClient.create(AppConstants.RequestPath.GET_COMMENT, map).tag("").get(callback);
    }

    /**
     * 发送评论到服务器
     */
    public OkHttpRequest sendComments(Replay replay, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("content", "");
        paramsMap.put("nid", "");
        paramsMap.put("pid", "");
        paramsMap.put("type", "");
        return ApiClient.create(AppConstants.RequestPath.AlipayQuery, paramsMap).tag("").get(callback);
    }
}
