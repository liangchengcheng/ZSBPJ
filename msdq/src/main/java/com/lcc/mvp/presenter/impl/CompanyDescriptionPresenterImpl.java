package com.lcc.mvp.presenter.impl;

import android.os.Handler;

import com.lcc.entity.Article;
import com.lcc.entity.CompanyDescription;
import com.lcc.entity.TestEntity;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.CompanyDescriptionModel;
import com.lcc.mvp.model.TestModel;
import com.lcc.mvp.presenter.CompanyDescriptionPresenter;
import com.lcc.mvp.presenter.TestPresenter;
import com.lcc.mvp.view.CompanyDescriptionView;
import com.lcc.mvp.view.TestView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import zsbpj.lccpj.frame.ApiException;
import zsbpj.lccpj.utils.GsonUtils;
import zsbpj.lccpj.utils.TimeUtils;

public class CompanyDescriptionPresenterImpl implements CompanyDescriptionPresenter {

    private static final int DEF_DELAY = (int) (1 * 1000);
    private CompanyDescriptionModel model;
    private CompanyDescriptionView view;

    public CompanyDescriptionPresenterImpl(CompanyDescriptionView view) {
        this.view = view;
        model = new CompanyDescriptionModel();
    }

    private void loadData(final int page,final boolean get_data) {
        if (get_data) {
            view.getLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        model.getCompanyDescriptionList(page, new ResultCallback<String>() {
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
                updateView(response, delay, page,get_data);
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
                           List<CompanyDescription> weekDatas = GsonUtils.fromJsonArray(result, CompanyDescription.class);
                           if (page == 1) {
                               if (weekDatas != null && weekDatas.size() > 0) {
                                   view.refreshView(weekDatas);
                               } else {
                                   view.getDataEmpty();
                               }
                           } else {
                               view.loadMoreView(weekDatas);
                           }
                       } else {
                           if (message.equals("数据为空") && page == 1) {
                               view.getDataEmpty();
                           } else {
                               if (get_data) {
                                   view.getDataFail(ApiException.getApiExceptionMessage(message));
                               } else {
                                   view.refreshOrLoadFail(ApiException.getApiExceptionMessage(message));
                               }
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
    public void getData(int page) {
        loadData(page, true);
    }

    @Override
    public void loadMore(int page) {
        loadData(page,false);
    }

    @Override
    public void refresh() {
        loadData(1,false);
    }
}
