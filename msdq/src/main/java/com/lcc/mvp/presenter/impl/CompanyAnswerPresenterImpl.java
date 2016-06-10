package com.lcc.mvp.presenter.impl;

import android.os.Handler;

import com.lcc.entity.Answer;
import com.lcc.entity.CompanyAnswer;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.CompanyAnswerModel;
import com.lcc.mvp.model.TestAnswerModel;
import com.lcc.mvp.presenter.CompanyAnswerPresenter;
import com.lcc.mvp.presenter.TestAnswerPresenter;
import com.lcc.mvp.view.CompanyAnswerView;
import com.lcc.mvp.view.TestAnswerView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import zsbpj.lccpj.utils.GsonUtils;
import zsbpj.lccpj.utils.TimeUtils;

public class CompanyAnswerPresenterImpl implements CompanyAnswerPresenter {

    private static final int DEF_DELAY = (int) (1 * 1000);
    private CompanyAnswerModel model;
    private CompanyAnswerView view;

    public CompanyAnswerPresenterImpl(CompanyAnswerView view) {
        this.view = view;
        model = new CompanyAnswerModel();
    }

    private void loadData(final int page,final String fid) {
        final long current_time = TimeUtils.getCurrentTime();
        model.getCompanyAnswer(page,fid, new ResultCallback<String>() {
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
                       String data=jsonObject.getString("result");
                       JSONObject result=new JSONObject(data);
                       String page_data=result.getString("data");
                       List<CompanyAnswer> entitys= GsonUtils.fromJsonArray(page_data,CompanyAnswer.class);
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
    public void loadMore(int page,String fid) {
        loadData(page,fid);
    }

    @Override
    public void refresh(int page,String fid) {
        loadData(page,fid);
    }
}
