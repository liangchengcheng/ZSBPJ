package com.lcc.mvp.presenter.impl;

import android.os.Handler;

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

    private void loadData(final int page) {
        final long current_time = TimeUtils.getCurrentTime();
        model.getCompanyDescriptionList(page, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(String response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, page);
            }
        });
    }

    private void updateView(final String entities, int delay, final int page) {

           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   try{
                       JSONObject jsonObject=new JSONObject(entities);
                       String data=jsonObject.getString("data");
                       List<CompanyDescription> entitys= GsonUtils.fromJsonArray(data,CompanyDescription.class);
                       if (page == 1) {
                           view.refreshView(entitys);
                       } else {
                           view.loadMoreView(entitys);
                       }
                   }catch (Exception e){
                       view.showError();
                       e.printStackTrace();
                   }
               }
           }, delay);
    }

    @Override
    public void loadMore(int page) {
        loadData(page);
    }

    @Override
    public void refresh() {
        loadData(1);
    }
}