package view.lcc.wyzsb.mvp.model;

import java.util.HashMap;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.mvp.param.Replay;
import view.lcc.wyzsb.mvp.param.SendComments;
import view.lcc.wyzsb.utils.UserSharePreferenceUtil;

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
        paramsMap.put("o_id", nid);
        return ApiClient.create(AppConstants.RequestPath.GET_COMMENT, paramsMap).tag("").get(callback);
    }

    /**
     * 发送评论到服务器
     */
    public OkHttpRequest sendComments(SendComments replay, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("content", replay.getContent());
        paramsMap.put("user_id", UserSharePreferenceUtil.getUserPhone());
        paramsMap.put("user_name", UserSharePreferenceUtil.getUserName());
        paramsMap.put("user_image", UserSharePreferenceUtil.getUserImage());
        paramsMap.put("oid", replay.getOid());
        paramsMap.put("type", "video");
        return ApiClient.create(AppConstants.RequestPath.HCommentsAdd, paramsMap).tag("").get(callback);
    }
}
