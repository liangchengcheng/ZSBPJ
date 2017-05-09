package view.lcc.wyzsb.mvp.model;

import view.lcc.wyzsb.base.ApiClient;
import view.lcc.wyzsb.base.AppConstants;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.frame.ParamsMap;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.frame.okhttp.request.OkHttpRequest;
import view.lcc.wyzsb.utils.UserSharePreferenceUtil;

public class VideoDetailsModel {

    /**
     * 获取详情判断物品的收藏
     */
    public OkHttpRequest getData(String vid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("vid", vid);
        paramsMap.put("phone", UserSharePreferenceUtil.getUserPhone());
        return ApiClient.create(AppConstants.RequestPath.getVideoFavState, paramsMap).tag("").get(callback);
    }

    /**
     * 收藏的这个视频
     */
    public OkHttpRequest favVideo(Video video, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("vid", video.getId());
        paramsMap.put("phone", UserSharePreferenceUtil.getUserPhone());
        paramsMap.put("v_t", video.getV_t());
        paramsMap.put("v_a", video.getV_a());
        paramsMap.put("v_img", video.getV_img());
        paramsMap.put("v_url", video.getV_url());
        paramsMap.put("v_time", video.getV_time());
        paramsMap.put("v_type", video.getV_type());
        paramsMap.put("v_js", video.getV_js());
        paramsMap.put("v_c", video.getV_c());
        paramsMap.put("v_l", video.getV_l());
        paramsMap.put("bq", video.getBq());
        return ApiClient.create(AppConstants.RequestPath.videoFavAdd, paramsMap).addHeader("phone","").post(callback);
    }

    /**
     * 取消收藏的原生资料的问题
     */
    public OkHttpRequest UnFavVideo(String vid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("vid", vid);
        paramsMap.put("phone", UserSharePreferenceUtil.getUserPhone());
        return ApiClient.create(AppConstants.RequestPath.deleteVideo, paramsMap).get(callback);
    }

    /**
     * 历史记录
     */
    public OkHttpRequest historyVideo(Video video, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("vid", video.getId());
        paramsMap.put("phone", UserSharePreferenceUtil.getUserPhone());
        paramsMap.put("v_t", video.getV_t());
        paramsMap.put("v_a", video.getV_a());
        paramsMap.put("v_img", video.getV_img());
        paramsMap.put("v_url", video.getV_url());
        paramsMap.put("v_time", video.getV_time());
        paramsMap.put("v_type", video.getV_type());
        paramsMap.put("v_js", video.getV_js());
        paramsMap.put("v_c", video.getV_c());
        paramsMap.put("v_l", video.getV_l());
        paramsMap.put("bq", video.getBq());
        return ApiClient.create(AppConstants.RequestPath.videoHisAdd, paramsMap).addHeader("phone","").post(callback);
    }

    /**
     * 取消收藏
     */
    public OkHttpRequest deleteFav(String vid, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("vid", vid);
        paramsMap.put("phone", UserSharePreferenceUtil.getUserPhone());
        return ApiClient.create(AppConstants.RequestPath.deleteFav, paramsMap).addHeader("phone","").post(callback);
    }

}
