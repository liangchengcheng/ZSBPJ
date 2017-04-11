package view.lcc.wyzsb.mvp.presenter.impl;

import android.os.Handler;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.ArticleModel;
import view.lcc.wyzsb.mvp.param.ArticleParams;
import view.lcc.wyzsb.mvp.presenter.ArticlePresenter;
import view.lcc.wyzsb.mvp.view.ArticleView;
import view.lcc.wyzsb.utils.GsonUtils;
import view.lcc.wyzsb.utils.TimeUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class ArticlePresenterImpl implements ArticlePresenter {

    private ArticleView view;
    private ArticleModel model;
    private static final int DEF_DELAY = (int) (1 * 1000);

    public ArticlePresenterImpl(ArticleView view) {
        this.view = view;
        model = new ArticleModel();
    }

    private void loadData(final int page, String type, final boolean get_data) {
        if (get_data) {
            view.getLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        ArticleParams params = new ArticleParams();
        model.getArticle(params, new ResultCallback<String>() {
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
                    String result = jsonObject.getString("result");
                    if (status == 1) {
                        List<Article> weekDatas = GsonUtils.fromJsonArray(result, Article.class);
                        if (page == 1) {
                            view.refreshDataSuccess(weekDatas);
                        } else {
                            view.loadMoreWeekDataSuccess(weekDatas);
                        }
                    } else if (status == 2) {
                        view.getDataFail(message);
                    } else {
                        if (get_data) {
                            view.getDataFail(message);
                        } else {
                            view.refreshOrLoadFail(message);
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
    public void getData(int page, String type) {
        loadData(page, type, true);
    }

    @Override
    public void loadMore(int page, String type) {
        loadData(page, type, false);
    }

    @Override
    public void refresh(int page, String type) {
        loadData(page, type, false);
    }

}

