package view.lcc.wyzsb.mvp.presenter.impl;

import android.os.Handler;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.News;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.NewsModel;
import view.lcc.wyzsb.mvp.model.VideoModel;
import view.lcc.wyzsb.mvp.param.NewsParams;
import view.lcc.wyzsb.mvp.param.VideoParams;
import view.lcc.wyzsb.mvp.presenter.NewsPresenter;
import view.lcc.wyzsb.mvp.presenter.VideoPresenter;
import view.lcc.wyzsb.mvp.view.NewsView;
import view.lcc.wyzsb.mvp.view.VideoView;
import view.lcc.wyzsb.utils.GsonUtils;
import view.lcc.wyzsb.utils.TimeUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class VideoPresenterImpl implements VideoPresenter {
    private static final int DEF_DELAY = (int) (1 * 1000);
    private VideoModel model;
    private VideoView view;

    public VideoPresenterImpl(VideoView view) {
        this.view = view;
        model = new VideoModel();
    }

    private void loadData(final VideoParams videoParams, final boolean get_data) {
        if (get_data) {
            view.getLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        model.getVideo(videoParams, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                if (get_data) {
                    view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
                } else {
                    view.refreshOrLoadFail(ApiException.getApiExceptionMessage(e.getMessage()));
                }
            }

            @Override
            public void onResponse(String response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, videoParams.getPage(), get_data);
            }
        });
    }

    private void updateView(final String entities, int delay, final int page, final boolean get_data) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(entities);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status ==1) {
                        String result = jsonObject.getString("result");
                        List<Video> weekDatas = GsonUtils.fromJsonArray(result, Video.class);
                        if (page == 1) {
                            view.refreshView(weekDatas);
                        } else {
                            view.loadMoreView(weekDatas);
                        }
                    } else {
                        if (get_data) {
                            view.getDataFail(ApiException.getApiExceptionMessage(""));
                        } else {
                            view.refreshOrLoadFail(ApiException.getApiExceptionMessage(""));
                        }
                    }
                } catch (Exception e) {
                    if (get_data) {
                        view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    } else {
                        view.refreshOrLoadFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    }
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    @Override
    public void getData(VideoParams params) {
        loadData(params, true);
    }

    @Override
    public void loadMore(VideoParams params) {
        loadData(params, false);
    }

    @Override
    public void refresh(VideoParams params) {
        loadData(params, false);
    }
}
