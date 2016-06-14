package com.lcc.mvp.presenter.impl;

import com.lcc.entity.Article;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.IndexContentModel;
import com.lcc.mvp.model.MenuContentModel;
import com.lcc.mvp.presenter.IndexContentPresenter;
import com.lcc.mvp.presenter.MenuContentPresenter;
import com.lcc.mvp.view.IndexContentView;
import com.lcc.mvp.view.MenuContentView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import zsbpj.lccpj.frame.ApiException;

public class MenuContentPresenterImpl implements MenuContentPresenter {

    private MenuContentView view;
    private MenuContentModel model;

    public MenuContentPresenterImpl(MenuContentView view) {
        this.view = view;
        model = new MenuContentModel();
    }

    @Override
    public void getArticleContent(String id) {
        model.getArticleContent(id,new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    String result = jsonObject.getString("result");
                    if (status == 1) {
                        JSONObject json_result = new JSONObject(result);
                        String content=json_result.getString("content");
                        view.getSuccess(content);
                    } else {
                        view.getFail(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void Fav(Article article, String type) {
        model.favArticle(article,type, new ResultCallback<String>() {
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
}

