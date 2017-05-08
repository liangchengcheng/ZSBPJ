package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;
import org.json.JSONObject;
import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.bean.Videofav;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.VideoDetailsModel;
import view.lcc.wyzsb.mvp.presenter.VideoDetailsPresenter;
import view.lcc.wyzsb.mvp.view.VideoDetailsView;
import view.lcc.wyzsb.utils.GsonUtils;

/**
 * 视频的详情（视频的历史记录+1 和  收藏视频）
 */
public class VideoDetailsPresenterImpl implements VideoDetailsPresenter {

    private VideoDetailsModel model;
    private VideoDetailsView view;

    public VideoDetailsPresenterImpl(VideoDetailsView view) {
        this.view = view;
        model = new VideoDetailsModel();
    }

    @Override
    public void getData(String vid) {
        model.getData(vid, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getDataFail("获取状态失败");
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String result = jsonObject.getString("result");
                    if (status == 1 ) {
                        Videofav fav = GsonUtils.changeGsonToBean(result, Videofav.class);
                        view.getDataSuccess(fav);
                    }  else {
                        view.getDataFail("获取状态失败");
                    }

                } catch (Exception e) {
                    view.getDataFail("获取状态失败");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void favVideo(Video video) {
        model.favVideo(video, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.FavFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        view.FavSuccess();
                    } else {
                        view.FavFail(message);
                    }
                } catch (Exception e) {
                    view.FavFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void historyVideo(Video video) {
        model.historyVideo(video, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.LookHistoryFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        view.LookHistory();
                    } else {
                        view.LookHistoryFail(message);
                    }
                } catch (Exception e) {
                    view.LookHistoryFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });
    }
}
