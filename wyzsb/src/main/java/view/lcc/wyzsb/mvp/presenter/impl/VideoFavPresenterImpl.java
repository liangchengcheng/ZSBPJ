package view.lcc.wyzsb.mvp.presenter.impl;

import android.os.Handler;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.History;
import view.lcc.wyzsb.bean.Videofav;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.HistoryModel;
import view.lcc.wyzsb.mvp.model.VideoFavModel;
import view.lcc.wyzsb.mvp.param.HistoryParams;
import view.lcc.wyzsb.mvp.param.VideoFavParams;
import view.lcc.wyzsb.mvp.presenter.HistoryPresenter;
import view.lcc.wyzsb.mvp.presenter.VideoFavPresenter;
import view.lcc.wyzsb.mvp.view.HistoryView;
import view.lcc.wyzsb.mvp.view.VideoFavView;
import view.lcc.wyzsb.utils.GsonUtils;
import view.lcc.wyzsb.utils.TimeUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class VideoFavPresenterImpl implements VideoFavPresenter {
    private static final int DEF_DELAY = (int) (1 * 1000);
    private VideoFavModel model;
    private VideoFavView view;

    public VideoFavPresenterImpl(VideoFavView view) {
        this.view = view;
        model = new VideoFavModel();
    }

    private void loadData(final int page, final boolean get_data) {
        if (get_data) {
            view.getLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        VideoFavParams params = new VideoFavParams();
        params.setPage(page);
        model.getVideoFav(params, new ResultCallback<String>() {
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
                updateView(response, delay, page, get_data);
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
                    if (status == 1) {
                        String result = jsonObject.getString("result");
                        List<Videofav> books = GsonUtils.fromJsonArray(result, Videofav.class);
                        if (page == 1) {
                            view.refreshView(books);
                        } else {
                            view.loadMoreView(books);
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
    public void getData(int page, String options) {
        loadData(page,  true);
    }

    @Override
    public void loadMore(int page, String options) {
        loadData(page,  false);
    }

    @Override
    public void refresh(int page, String options) {
        loadData(1,  false);
    }
}
