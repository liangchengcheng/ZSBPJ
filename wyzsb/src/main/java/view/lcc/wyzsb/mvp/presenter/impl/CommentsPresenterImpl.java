package view.lcc.wyzsb.mvp.presenter.impl;

import android.os.Handler;
import com.squareup.okhttp.Request;
import org.json.JSONObject;
import java.util.List;
import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.Comments;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.CommentsModel;
import view.lcc.wyzsb.mvp.param.Replay;
import view.lcc.wyzsb.mvp.param.SendComments;
import view.lcc.wyzsb.mvp.presenter.CommentsPresenter;
import view.lcc.wyzsb.mvp.view.CommentsView;
import view.lcc.wyzsb.utils.GsonUtils;
import view.lcc.wyzsb.utils.TimeUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class CommentsPresenterImpl implements CommentsPresenter{

    private CommentsView view;
    private CommentsModel model;
    private static final int DEF_DELAY = (int) (1 * 1000);

    public CommentsPresenterImpl(CommentsView view) {
        this.view = view;
        model = new CommentsModel();
    }

    private void loadData(final int page, String nid, final boolean get_data) {
        if (get_data) {
            view.getLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        model.getComments(page, nid, new ResultCallback<String>() {
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
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if (status.equals("1")) {
                        String result = jsonObject.getString("result");
                        List<Comments> weekDatas = GsonUtils.fromJsonArray(result, Comments.class);
                        if (page == 1) {
                            view.refreshDataSuccess(weekDatas);
                        } else {
                            view.loadMoreWeekDataSuccess(weekDatas);
                        }
                    }  else {
                        if (get_data) {
                            view.getDataFail("");
                        } else {
                            view.refreshOrLoadFail("");
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
    public void getData(int page, String nid) {
        loadData(page, nid, true);
    }

    @Override
    public void loadMore(int page, String nid) {
        loadData(page, nid, false);
    }

    @Override
    public void refresh(int page, String nid) {
        loadData(page, nid, false);
    }

    @Override
    public void sendComments(SendComments replay) {
        model.sendComments(replay, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.replayFail();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        view.replaySuccess();
                    }else if (status == 2) {
                        view.replayFail();
                    } else {
                        view.replayFail();
                    }
                } catch (Exception e) {
                    view.replayFail();
                    e.printStackTrace();
                }
            }
        });
    }
}
