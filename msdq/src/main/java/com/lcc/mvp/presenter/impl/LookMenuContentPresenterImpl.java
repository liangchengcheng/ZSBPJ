package com.lcc.mvp.presenter.impl;

import com.lcc.entity.Article;
import com.lcc.entity.ArticleContent;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.MenuContentModel;
import com.lcc.mvp.presenter.LookMenuContentPresenter;
import com.lcc.mvp.presenter.MenuContentPresenter;
import com.lcc.mvp.view.MenuContentView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import zsbpj.lccpj.frame.ApiException;
import zsbpj.lccpj.utils.GsonUtils;

public class LookMenuContentPresenterImpl implements LookMenuContentPresenter {

    private MenuContentView view;
    private MenuContentModel model;

    public LookMenuContentPresenterImpl(MenuContentView view) {
        this.view = view;
        model = new MenuContentModel();
    }

    @Override
    public void Fav(Article article, String type) {
        model.favArticle(article, type, new ResultCallback<String>() {
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
    public void UnFav(Article article) {
        model.UnfavArticle(article, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.UnFavFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        view.UnFavSuccess();
                    } else {
                        view.UnFavFail(message);
                    }
                } catch (Exception e) {
                    view.UnFavFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getArticleContentAndFav(String id) {
        model.getArticleContentAndFav(id, new ResultCallback<String>() {
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
                        ArticleContent articleContents = GsonUtils.changeGsonToBean(result, ArticleContent.class);
                        if (articleContents != null ) {
                            view.getSuccess(articleContents);
                        } else {
                            view.getFail("文章不存在");
                        }
                    } else {
                        view.getFail(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

