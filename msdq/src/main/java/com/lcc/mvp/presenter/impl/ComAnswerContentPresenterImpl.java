package com.lcc.mvp.presenter.impl;

import com.lcc.entity.Answer;
import com.lcc.entity.CompanyAnswer;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.ComAnswerContentModel;
import com.lcc.mvp.model.TestAnswerContentModel;
import com.lcc.mvp.presenter.ComAnswerContentPresenter;
import com.lcc.mvp.presenter.TestAnswerContentPresenter;
import com.lcc.mvp.view.ComAnswerContentView;
import com.lcc.mvp.view.TestAnswerContentView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import zsbpj.lccpj.frame.ApiException;

public class ComAnswerContentPresenterImpl implements ComAnswerContentPresenter {

    private ComAnswerContentModel model;
    private ComAnswerContentView view;

    public ComAnswerContentPresenterImpl(ComAnswerContentView view) {
        this.view = view;
        model = new ComAnswerContentModel();
    }

    @Override
    public void isFav(String nid) {
        model.isfavAnswer(nid, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.FavFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String result=jsonObject.getString("result");
                    if (status == 1&&!result.equals("[]")) {
                        view.isHaveFav(true);
                    } else {
                        view.isHaveFav(false);
                    }
                } catch (Exception e) {
                    view.FavFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void Fav(CompanyAnswer article, String type, String title) {
        model.favAnswer(article,type,title, new ResultCallback<String>() {
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
    public void UnFav(CompanyAnswer article) {
        model.UnfavAnswer(article, new ResultCallback<String>() {
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
}
