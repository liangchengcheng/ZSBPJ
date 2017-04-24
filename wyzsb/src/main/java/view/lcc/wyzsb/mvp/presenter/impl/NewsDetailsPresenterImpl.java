package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.bean.News;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.ArticleDetailsModel;
import view.lcc.wyzsb.mvp.model.NewsDetailsModel;
import view.lcc.wyzsb.mvp.presenter.ArticleDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.NewsDetailsPresenter;
import view.lcc.wyzsb.mvp.view.ArticleDetailsView;
import view.lcc.wyzsb.mvp.view.NewsDetailsView;
import view.lcc.wyzsb.utils.GsonUtils;

public class NewsDetailsPresenterImpl implements NewsDetailsPresenter {
    private NewsDetailsModel model;
    private NewsDetailsView view;

    public NewsDetailsPresenterImpl(NewsDetailsView view) {
        this.view = view;
        model = new NewsDetailsModel();
    }

    @Override
    public void getContent(String mid) {
        view.getLoading();
        model.getContent(mid, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    //这个是内容
                    if (status == 1) {
                        String result = jsonObject.getString("result");
                        News answerContent = GsonUtils.changeGsonToBean(result, News.class);
                        view.getDataSuccess(answerContent);
                    } else {
                        view.getDataFail(message);
                    }
                } catch (Exception e) {
                    view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });
    }
}
